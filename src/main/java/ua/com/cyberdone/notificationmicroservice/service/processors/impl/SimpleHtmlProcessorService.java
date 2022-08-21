package ua.com.cyberdone.notificationmicroservice.service.processors.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import ua.com.cyberdone.notificationmicroservice.model.CyberdoneMail;
import ua.com.cyberdone.notificationmicroservice.service.processors.TemplateProcessor;

import java.io.IOException;
import java.nio.file.Files;

@Slf4j
@Service("HTML")
@RequiredArgsConstructor
public class SimpleHtmlProcessorService implements TemplateProcessor {
    @Value("${spring.thymeleaf.models.path}")
    public String modelsPath;
    @Value("${spring.thymeleaf.models.suffix}")
    public String modelsSuffix;

    public String process(CyberdoneMail mail) throws IOException {
        var templateName = mail.getMailTemplate();
        var file = ResourceUtils.getFile(modelsPath + templateName + modelsSuffix);
        return Files.readString(file.toPath());
    }
}
