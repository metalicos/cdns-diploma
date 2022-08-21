package ua.com.cyberdone.notificationmicroservice.exceptions;

public class AttachmentException extends Exception{
    public AttachmentException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
