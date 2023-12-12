package com.example.api.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {
    private final JavaMailSender javaMailSender;
    @Async
    @Override
    public void sendMailWithAttachment(SendMailDto sendMailDto, String attachment){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper =
                    new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(sendMailDto.getTo());
            mimeMessageHelper.setFrom(sendMailDto.getFrom());
            mimeMessageHelper.setSubject(sendMailDto.getSubject());
            mimeMessageHelper.setText(sendMailDto.getMessage());
            File file = new File(attachment);
            FileSystemResource resource = new FileSystemResource(file);
            mimeMessageHelper.addAttachment(Objects.requireNonNull(resource.getFilename()), resource);
            System.out.println(resource.getFilename());
            javaMailSender.send(mimeMessage);
        }catch (MessagingException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void sendMail(SendMailDto sendMailDto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sendMailDto.getFrom());
        mailMessage.setTo(sendMailDto.getTo());
        mailMessage.setSubject(sendMailDto.getSubject());
        mailMessage.setText(sendMailDto.getMessage());
        javaMailSender.send(mailMessage);
    }

}
