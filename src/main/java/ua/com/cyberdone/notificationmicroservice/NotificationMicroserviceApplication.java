package ua.com.cyberdone.notificationmicroservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.com.cyberdone.notificationmicroservice.model.Template;
import ua.com.cyberdone.notificationmicroservice.service.EmailSendingService;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class NotificationMicroserviceApplication implements CommandLineRunner {
    private final EmailSendingService emailSendingService;

    public static void main(String[] args) {
        SpringApplication.run(NotificationMicroserviceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("START... Sending email");
        var model = Map.of("name", (Object) "Developer!", "location", "United States", "sign", "Java Developer");
        emailSendingService.sendMail("ostap.ja@gmail.com", "JustTitle", Template.SUBMIT_ACCOUNT_EMAIL, model);
        log.info("END... Email sent success");

    }
}