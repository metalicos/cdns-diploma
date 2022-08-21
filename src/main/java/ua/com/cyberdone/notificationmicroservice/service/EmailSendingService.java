package ua.com.cyberdone.notificationmicroservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.notificationmicroservice.exceptions.AttachmentException;
import ua.com.cyberdone.notificationmicroservice.model.CyberdoneMail;
import ua.com.cyberdone.notificationmicroservice.service.processors.TemplateProcessor;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailSendingService {

    private final JavaMailSender emailSender;
    private final MailAttachmentProcessorService attachmentProcessorService;
    private final Map<String, TemplateProcessor> processorMap;

    public void sendMail(CyberdoneMail mail) throws MessagingException, AttachmentException, IOException {
        var message = emailSender.createMimeMessage();

        var mimeMessageHelper = new MimeMessageHelper(message, MULTIPART_MODE_MIXED_RELATED, UTF_8.name());
        attachmentProcessorService.processAttachments(mimeMessageHelper, mail.getAttachmentUrls());

        var html = processorMap.get(mail.getTemplateType().name())
                .process(mail);

        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject(mail.getTitle());
        mimeMessageHelper.setFrom(mail.getCyberdoneServiceName());
        mimeMessageHelper.setTo(mail.getReceiverEmail());
        mimeMessageHelper.setPriority(mail.getPriority().getNumber());

        emailSender.send(message);
    }
}