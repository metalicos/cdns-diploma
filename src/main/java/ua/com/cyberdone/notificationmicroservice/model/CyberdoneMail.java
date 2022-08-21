package ua.com.cyberdone.notificationmicroservice.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CyberdoneMail {
    @NotBlank
    private String cyberdoneServiceName;
    @NotBlank
    private String receiverEmail;
    @NotBlank
    private String title;
    private Priority priority;
    private TemplateType templateType = TemplateType.THYMELEAF;
    @NotBlank
    private String mailTemplate;
    private Map<String, Object> variables;
    private Map<String, String> attachmentUrls;

    @RequiredArgsConstructor
    @Getter
    public enum Priority {
        LOW(5),
        MEDIUM(3),
        HIGH(1);

        private final int number;
    }

    public enum TemplateType {
        THYMELEAF,
        HTML
    }
}
