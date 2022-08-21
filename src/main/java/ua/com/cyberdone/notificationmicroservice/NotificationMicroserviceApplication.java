package ua.com.cyberdone.notificationmicroservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.com.cyberdone.notificationmicroservice.service.EmailSendingService;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class NotificationMicroserviceApplication {
    private final EmailSendingService emailSendingService;

    public static void main(String[] args) {
        SpringApplication.run(NotificationMicroserviceApplication.class, args);
    }
}