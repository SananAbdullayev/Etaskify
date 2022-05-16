package com.example.etaskifyiba.service.impl;

import com.example.etaskifyiba.dto.OrganizationDTO;
import com.example.etaskifyiba.dto.request.OrganizationRequest;
import com.example.etaskifyiba.exception.CustomNotFoundException;
import com.example.etaskifyiba.exception.handling.ErrorCodeEnum;
import com.example.etaskifyiba.model.entity.Organization;
import com.example.etaskifyiba.repository.OrganizationRepository;
import com.example.etaskifyiba.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;


    @Override
    public List<OrganizationDTO> getAllOrganization() {
        return organizationRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrganizationDTO getOrganizationById(Integer id) {
        return organizationRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new CustomNotFoundException(ErrorCodeEnum.ORGANIZATION_NOT_FOUND));
    }


    @Override
    public void update(OrganizationRequest organizationRequest, Integer id) {
        Organization organization = getOrgById(id);
        organization = Organization.builder()
                .id(organization.getId())
                .name(organizationRequest.getOrganizationName())
                .address(organizationRequest.getAddress())
                .phoneNumber(organizationRequest.getPhoneNumber())
                .task(organization.getTask())
                .user(organization.getUser())
                .build();

        organizationRepository.save(organization);
    }

    @Override
    public OrganizationDTO save(OrganizationRequest organizationRequest) {
        Organization org = Organization.builder()
                .name(organizationRequest.getOrganizationName())
                .phoneNumber(organizationRequest.getPhoneNumber())
                .address(organizationRequest.getAddress())
                .build();

        organizationRepository.save(org);
        return convertToDto(org);
    }

    @Override
    public void delete(Integer id) {
        Organization organization = getOrgById(id);
        organizationRepository.delete(organization);
    }


    private OrganizationDTO convertToDto(Organization organization) {
        return OrganizationDTO.builder()
                .id(organization.getId())
                .organizationName(organization.getName())
                .address(organization.getAddress())
                .phoneNumber(organization.getPhoneNumber())
                .build();
    }

    private Organization getOrgById(Integer id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException(ErrorCodeEnum.ORGANIZATION_NOT_FOUND));
    }
}
