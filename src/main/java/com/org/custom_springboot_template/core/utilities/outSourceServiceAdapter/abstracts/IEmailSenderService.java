package com.org.custom_springboot_template.core.utilities.outSourceServiceAdapter.abstracts;

import javax.mail.MessagingException;

public interface IEmailSenderService {

    void sendSimpleMessage(String to, String subject, String text);

    void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException;
}
