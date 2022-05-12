package ua.com.cyberdone.notificationmicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Template {
    SUBMIT_ACCOUNT_EMAIL("submit-account");

    private final String name;
}
