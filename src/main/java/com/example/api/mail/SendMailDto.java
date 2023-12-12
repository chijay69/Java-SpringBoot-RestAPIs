package com.example.api.mail;

import lombok.*;

@Getter
@Setter
public class SendMailDto {
    private String to;
    private String from;
    private String subject;
    private String message;
    private String attachment;
}