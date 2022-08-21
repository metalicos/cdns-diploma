package ua.com.cyberdone.notificationmicroservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.cyberdone.notificationmicroservice.exceptions.AttachmentException;
import ua.com.cyberdone.notificationmicroservice.model.CyberdoneMail;
import ua.com.cyberdone.notificationmicroservice.service.EmailSendingService;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/mail")
public class MailController {

    private final EmailSendingService emailSendingService;

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody @Valid CyberdoneMail cyberdoneMail) throws AttachmentException, MessagingException, IOException {
        emailSendingService.sendMail(cyberdoneMail);
        return ResponseEntity.ok("""
                Mail(
                  mail-template: %s
                  title: %s
                  service: %s
                  toReceiver: %s
                )
                Successfully sent.
                Date: %s
                """.formatted(
                cyberdoneMail.getMailTemplate(),
                cyberdoneMail.getTitle(),
                cyberdoneMail.getCyberdoneServiceName(),
                cyberdoneMail.getReceiverEmail(),
                new Date().toString()));
    }

}
