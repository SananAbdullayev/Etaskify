package com.example.etaskifyiba.dto.response;

import com.example.etaskifyiba.dto.UserDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private List<UserDTO> users;
}
