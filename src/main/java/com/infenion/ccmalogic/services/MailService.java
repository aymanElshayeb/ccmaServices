package com.infenion.ccmalogic.services;
import com.infenion.ccmamodel.model.Request;
import com.infenion.ccmamodel.model.Status;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

@Service
public class MailService {

    private final TemplateEngine templateEngine;

    private final JavaMailSender javaMailSender;

    public MailService(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }




    public String sendMail(String address , Request request) throws MessagingException {
        String process=null;
        String subject=null;
        Context context = new Context();
        context.setVariable("projectId", request.getProject().getId());
        context.setVariable("requestId", request.getId());
        context.setVariable("requesterId", request.getRequester().getId());
        context.setVariable("requesterName", request.getRequester().getUserName());
        context.setVariable("ProjectName", request.getProject().getName());
        context.setVariable("accessPermission", request.getSystemAccess().getAccessPermission().toString());
        context.setVariable("systemName",  request.getSystemAccess().getSystemName().toString());
        context.setVariable("requestStatus",request.getStatus());

        if (request.getStatus()== Status.PENDING) {
            process = templateEngine.process("managerMail", context);
            subject = "Access request for project : " + request.getProject().getName() + " from " + request.getRequester().getUserName();
        } else if (request.getStatus()== Status.COMPLETED || request.getStatus()== Status.DRAFT) {
            process = templateEngine.process("requesterMail", context);
            subject = "Response for access request for project : " + request.getProject().getName() ;
        }


        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(subject);

        helper.setText(process, true);
        helper.setTo(address);
        javaMailSender.send(mimeMessage);

        return "Sent";
    }
}
