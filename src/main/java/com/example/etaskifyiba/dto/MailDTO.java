package com.example.etaskifyiba.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailDTO {
    private String mailTo;
    private String mailSubject;
    private String mailContent;
    private String contentType = "text/plain";
}
