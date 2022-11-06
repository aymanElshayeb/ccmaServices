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



    public String sendMail(String address ,String subject, long requesterId,long projectId,long requestId) throws MessagingException {
        Context context = new Context();
        context.setVariable("projectId", projectId);
        context.setVariable("requestId", requestId);
        context.setVariable("requesterId", requesterId);


        String process = templateEngine.process("request", context);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(subject);
        helper.setText(process, true);
        helper.setTo(address);
        javaMailSender.send(mimeMessage);

        return "Sent";
    }
}
