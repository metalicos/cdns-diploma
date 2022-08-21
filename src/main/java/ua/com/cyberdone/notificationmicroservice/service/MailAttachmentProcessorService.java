package ua.com.cyberdone.notificationmicroservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ua.com.cyberdone.notificationmicroservice.exceptions.AttachmentException;

import javax.activation.URLDataSource;
import javax.mail.MessagingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

@Slf4j
@Service
public class MailAttachmentProcessorService {

    void processAttachments(MimeMessageHelper helper, Map<String, String> attachmentUrls) throws AttachmentException {
        if (attachmentUrls == null || attachmentUrls.isEmpty()) {
            log.info("Mail without any attachment");
            return;
        }
        for (var attachmentEntry : attachmentUrls.entrySet()) {
            try {
                var dataSource = new URLDataSource(new URL(attachmentEntry.getValue()));
                helper.addAttachment(attachmentEntry.getKey(), dataSource);
            } catch (MalformedURLException e) {
                log.error("Mail attachment url='{}' is malformed. Attachment is skipped.", attachmentEntry.getValue());
                throw new AttachmentException(e.getMessage(), e);
            } catch (MessagingException e) {
                log.error("Add attachment error.", e);
                throw new AttachmentException(e.getMessage(), e);
            }
        }
    }
}
