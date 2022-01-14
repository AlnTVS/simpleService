package ru.bellintegrator.simpleservice.office.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.simpleservice.office.service.OfficeService;
import ru.bellintegrator.simpleservice.office.view.OfficeView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "OfficeController", description = "Управление информацией об офисах")
@RestController
@RequestMapping(value = "/office", produces = APPLICATION_JSON_VALUE)
public class OfficeController {

    public OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @ApiOperation(value = "Получить список всех офисов", httpMethod = "POST")
    @PostMapping("/list")
    public List<OfficeView> offices() {
        return officeService.offices();
    }
}
