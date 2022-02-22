package ru.bellintegrator.simpleservice.citizenship.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.simpleservice.citizenship.service.CitizenshipService;
import ru.bellintegrator.simpleservice.citizenship.view.CitizenshipView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "CitizenshipController", description = "Справочник по странам")
@RestController
@RequestMapping(value = "/countries", produces = APPLICATION_JSON_VALUE)
public class CitizenshipController {

    private CitizenshipService citizenshipService;

    public CitizenshipController(CitizenshipService citizenshipService) {
        this.citizenshipService = citizenshipService;
    }

    @GetMapping
    public List<CitizenshipView> handbookCountries(){
        return citizenshipService.getAllCountries();
    }
}
