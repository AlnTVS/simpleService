package ru.bellintegrator.simpleservice.citizenship.service;

import ru.bellintegrator.simpleservice.citizenship.view.CitizenshipView;

import java.util.List;

public interface CitizenshipService {

    public List<CitizenshipView> getAllCountries();

}
