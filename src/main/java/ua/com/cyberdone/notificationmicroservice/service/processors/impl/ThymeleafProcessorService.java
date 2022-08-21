package ua.com.cyberdone.notificationmicroservice.service.processors.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import ua.com.cyberdone.notificationmicroservice.model.CyberdoneMail;
import ua.com.cyberdone.notificationmicroservice.service.processors.TemplateProcessor;

@Slf4j
@Service("THYMELEAF")
@RequiredArgsConstructor
public class ThymeleafProcessorService implements TemplateProcessor {
    private final SpringTemplateEngine templateEngine;

    public String process(CyberdoneMail mail) {
        var context = new Context();
        context.setVariables(mail.getVariables());
        return templateEngine.process(mail.getMailTemplate(), context);
    }
}
