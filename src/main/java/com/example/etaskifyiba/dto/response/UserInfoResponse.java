package com.example.etaskifyiba.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
    private long id;
    private String username;
    private String email;
    private List<String> roles;
}
