package ru.bellintegrator.simpleservice.organization.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.simpleservice.organization.service.OrganizationService;
import ru.bellintegrator.simpleservice.organization.view.OrganizationForHTTPMethodsExtendedView;
import ru.bellintegrator.simpleservice.organization.view.OrganizationForHTTPMethodListView;

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
    @PostMapping("/list")
    public List<OrganizationForHTTPMethodListView> organizations(@RequestBody(required=false) OrganizationForHTTPMethodListView organization) {
        List<OrganizationForHTTPMethodListView> organizationForHTTPMethodListViewList = null;
        if (organization != null) {
            organizationForHTTPMethodListViewList = organizationService.organizations(organization);
        } else
        {
            organizationForHTTPMethodListViewList = organizationService.organizations();
        }
        return organizationForHTTPMethodListViewList;
    }

    @ApiOperation(value = "Получить организацию по id", httpMethod = "GET")
    @GetMapping("/{id}")
    public OrganizationForHTTPMethodsExtendedView organisationById(@PathVariable Long id) {
        return organizationService.organisationById(id);
    }

    @ApiOperation(value = "Обновить информацию об организации", httpMethod = "POST")
    @PostMapping("/update")
    public void updateOrganization(@RequestBody OrganizationForHTTPMethodsExtendedView organizationForHTTPMethodsExtendedView) {
        organizationService.updateOrganization(organizationForHTTPMethodsExtendedView);
        return;
    }

    @ApiOperation(value = "Добавить новую организацию", httpMethod = "POST")
    @PostMapping("/save")
    public void addNewOrganization(@RequestBody OrganizationForHTTPMethodsExtendedView organizationForHTTPMethodsExtendedView) {
        organizationService.addNewOrganization(organizationForHTTPMethodsExtendedView);
        return;
    }
}