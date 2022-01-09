package ru.bellintegrator.simpleService.organization.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.simpleService.organization.entity.OrganizationEntity;
import ru.bellintegrator.simpleService.organization.service.OrganizationService;
import ru.bellintegrator.simpleService.organization.util.OrganizationFilter;
import ru.bellintegrator.simpleService.organization.view.FullOrganizationView;
import ru.bellintegrator.simpleService.organization.view.OrganizationView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "OrganizationController", description = "Управление информацией об организациях")
@RestController
@RequestMapping(value = "/organization", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @ApiOperation(value = "Получить список всех организаций", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/list")
    public List<OrganizationView> organizations(@RequestBody(required=false) OrganizationView organization) {
        List<OrganizationView> organizationViewList = null;
        if (organization != null) {
            OrganizationFilter organizationFilter = new OrganizationFilter(organization);
            organizationViewList = organizationService.organizations(organizationFilter.getSpec());
        } else
        {
            organizationViewList = organizationService.organizations();
        }
        return organizationViewList;
    }

    @ApiOperation(value = "Получить организацию по id", httpMethod = "GET")
    @GetMapping("/{id}")
    public FullOrganizationView organisationById(@PathVariable Long id) {
        return organizationService.organisationById(id);
    }

}