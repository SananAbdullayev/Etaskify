package com.example.etaskifyiba.service.impl;

import com.example.etaskifyiba.dto.MailDTO;
import com.example.etaskifyiba.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender javaMailSender;

    @Override
    public void sendMail(MailDTO mailDTO) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setTo(mailDTO.getMailTo());
            mimeMessageHelper.setSubject(mailDTO.getMailSubject());
            mimeMessageHelper.setText(mailDTO.getMailContent());

            javaMailSender.send(mimeMessageHelper.getMimeMessage());

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
