package com.example.etaskifyiba.controller;

import com.example.etaskifyiba.dto.OrganizationDTO;
import com.example.etaskifyiba.dto.request.OrganizationRequest;
import com.example.etaskifyiba.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationService organizationService;


    @GetMapping
    public List<OrganizationDTO> getAllOrganization(){
        return organizationService.getAllOrganization();
    }

    @GetMapping("/{id}")
    public OrganizationDTO getById(@PathVariable(name = "id") Integer id) {
        return organizationService.getOrganizationById(id);
    }

    @PutMapping("/{id}")
    public void updateOrganizationById(@PathVariable(name = "id") Integer id,
                                       @Valid @RequestBody OrganizationRequest organizationRequest) {
        organizationService.update(organizationRequest, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganizationById(@PathVariable(name = "id") Integer id) {
        organizationService.delete(id);
    }
}
