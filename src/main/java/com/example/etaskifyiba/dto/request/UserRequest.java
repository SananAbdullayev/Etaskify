package com.example.etaskifyiba.dto.request;

import com.example.etaskifyiba.validator.UniqueEmail;
import com.example.etaskifyiba.validator.UniqueUsername;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    @UniqueUsername
    private String username;
    @Email
    @NotBlank
    @UniqueEmail
    private String email;
    @Size(min = 6)
    private String password;
}
