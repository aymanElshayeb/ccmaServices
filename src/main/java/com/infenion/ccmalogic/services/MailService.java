package com.infenion.ccmalogic.services;
import com.infenion.ccmamodel.model.Request;
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
        Context context = new Context();
        context.setVariable("projectId", request.getProject().getId());
        context.setVariable("requestId", request.getId());
        context.setVariable("requesterId", request.getRequester().getId());
        context.setVariable("requesterName", request.getRequester().getUserName());
        context.setVariable("ProjectName", request.getProject().getName());
        context.setVariable("accessPermission", request.getSystemAccess().getAccessPermission().toString());
        context.setVariable("systemName",  request.getSystemAccess().getSystemName().toString());

        String process = templateEngine.process("request", context);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Access request for project : "+request.getProject().getName()+" from "+request.getRequester().getUserName());

        helper.setText(process, true);
        helper.setTo(address);
        helper.setFrom("ccma@infineon.com");
        javaMailSender.send(mimeMessage);

        return "Sent";
    }
}
