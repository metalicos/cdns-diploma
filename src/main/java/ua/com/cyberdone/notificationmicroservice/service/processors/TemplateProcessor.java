package ua.com.cyberdone.notificationmicroservice.service.processors;

import ua.com.cyberdone.notificationmicroservice.model.CyberdoneMail;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface TemplateProcessor {

    String process(CyberdoneMail mail) throws IOException;
}
