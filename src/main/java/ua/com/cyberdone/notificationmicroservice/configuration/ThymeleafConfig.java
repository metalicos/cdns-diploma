package ua.com.cyberdone.notificationmicroservice.configuration;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.nio.charset.StandardCharsets;

@Setter
@Configuration
public class ThymeleafConfig {

    @Value("${spring.thymeleaf.cache.ttl.ms}")
    public long ttlMs;

    @Value("${spring.thymeleaf.cache.enabled}")
    public boolean cacheEnabled;

    @Value("${spring.thymeleaf.models.path}")
    public String modelsPath;

    @Value("${spring.thymeleaf.models.suffix}")
    public String modelsSuffix;

    @Bean
    public SpringTemplateEngine springTemplateEngine() {
        var templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        var templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix(modelsPath);
        templateResolver.setSuffix(modelsSuffix);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        templateResolver.setCacheable(cacheEnabled);
        templateResolver.setCacheTTLMs(ttlMs);
        return templateResolver;
    }

// TODO As alternative version of loading templates from string
//    @Bean
//    public ITemplateResolver templateResolver() {
//        var stringTemplateResolver = new StringTemplateResolver();
//        stringTemplateResolver.setTemplateMode(TemplateMode.HTML);
//        stringTemplateResolver.setCacheable(cacheEnabled);
//        stringTemplateResolver.setCacheTTLMs(ttlMs);
//        return stringTemplateResolver;
//    }

}
