package com.infenion.ccmalogic.services;
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



    public String sendMail(String address ,String requesterName,String ProjectName, long requesterId,long projectId,long requestId,String accessPermission,String systemName) throws MessagingException {
        Context context = new Context();
        context.setVariable("projectId", projectId);
        context.setVariable("requestId", requestId);
        context.setVariable("requesterId", requesterId);
        context.setVariable("requesterName", requesterName);
        context.setVariable("ProjectName", ProjectName);
        context.setVariable("accessPermission", accessPermission);
        context.setVariable("systemName", systemName);

        String process = templateEngine.process("request", context);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("access request for project : "+ProjectName+" from "+requesterName);

        helper.setText(process, true);
        helper.setTo(address);
        javaMailSender.send(mimeMessage);

        return "Sent";
    }
}
