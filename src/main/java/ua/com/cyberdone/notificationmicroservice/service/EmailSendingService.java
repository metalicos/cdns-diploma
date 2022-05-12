package ua.com.cyberdone.notificationmicroservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import ua.com.cyberdone.notificationmicroservice.model.Template;

import javax.mail.MessagingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailSendingService {

    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;

    public void sendMail(String emailReceiver, String title, Template templateType, Map<String, Object> variables) throws MessagingException {
        var message = emailSender.createMimeMessage();
        var helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

        var context = new Context();
        context.setVariables(variables);

        var html = templateEngine.process(templateType.getName(), context);

        helper.setText(html, true);
        helper.setSubject(title);
        helper.setFrom("CyberDone");
        helper.setTo(emailReceiver);

        emailSender.send(message);
    }
}