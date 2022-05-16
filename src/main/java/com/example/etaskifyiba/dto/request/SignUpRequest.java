package com.example.etaskifyiba.dto.request;

import com.example.etaskifyiba.validator.UniqueEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @Email
    @NotBlank
    @UniqueEmail
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String organizationName;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String address;
}
