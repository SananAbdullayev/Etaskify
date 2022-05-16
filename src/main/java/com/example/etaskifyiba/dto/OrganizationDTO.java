package com.example.etaskifyiba.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationDTO {
    private Integer id;
    private String organizationName;
    private String phoneNumber;
    private String address;
}