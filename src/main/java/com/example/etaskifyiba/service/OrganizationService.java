package com.example.etaskifyiba.service;

import com.example.etaskifyiba.dto.OrganizationDTO;
import com.example.etaskifyiba.dto.request.OrganizationRequest;

import java.util.List;

public interface OrganizationService {
    OrganizationDTO getOrganizationById(Integer id);

    void update(OrganizationRequest organizationRequest, Integer id);

    void delete(Integer id);

    OrganizationDTO save(OrganizationRequest organizationRequest);

    List<OrganizationDTO> getAllOrganization();
}
