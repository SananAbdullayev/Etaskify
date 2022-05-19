package com.example.etaskifyiba.service;

import com.example.etaskifyiba.dto.MailDTO;

import javax.mail.MessagingException;

public interface MailService {
    void sendMail(MailDTO mailDTO) throws MessagingException;
}
