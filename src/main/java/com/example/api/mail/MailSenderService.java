package com.example.api.mail;

public interface MailSenderService {
    void sendMailWithAttachment(SendMailDto sendMailDto, String attachment);

    void sendMail(SendMailDto sendMailDto);
}
