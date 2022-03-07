package ru.bellintegrator.simpleservice.office.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.simpleservice.office.service.OfficeService;
import ru.bellintegrator.simpleservice.office.view.FullOfficeView;
import ru.bellintegrator.simpleservice.office.view.OfficeForHTTPMethodListView;

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
    public List<OfficeForHTTPMethodListView> offices(@RequestBody(required = false) OfficeForHTTPMethodListView officeForHTTPMethodListView) {
        List<OfficeForHTTPMethodListView> officeForHTTPMethodListViews = null;
        if (officeForHTTPMethodListView != null) {
            officeForHTTPMethodListViews = officeService.offices(officeForHTTPMethodListView);
        } else {
            officeForHTTPMethodListViews = officeService.offices();
        }
        return officeForHTTPMethodListViews;
    }

    @ApiOperation(value = "Получить офис по id", httpMethod = "GET")
    @GetMapping("/{id}")
    public FullOfficeView officeById(@PathVariable Long id) {
        return officeService.officeById(id);
    }

    @ApiOperation(value = "Обновить информацию по офису", httpMethod = "POST")
    @PostMapping("/update")
    public String updateOffice(@RequestBody FullOfficeView fullOfficeView) {
        officeService.updateOfficeByFullView(fullOfficeView);
        return "\"result\":\"success\"";
    }

    @ApiOperation(value = "Добавить новый офис", httpMethod = "POST")
    @PostMapping("/save")
    public String addNewOffice(@RequestBody FullOfficeView fullOfficeView) {
        officeService.addNewOffice(fullOfficeView);
        return "\"result\":\"success\"";
    }
}
