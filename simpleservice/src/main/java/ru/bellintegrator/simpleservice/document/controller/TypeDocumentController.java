package ru.bellintegrator.simpleservice.document.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.simpleservice.document.service.TypeDocumentService;
import ru.bellintegrator.simpleservice.document.view.TypeDocumentView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "DocumentController", description = "Справочник по документам")
@RestController
@RequestMapping(value = "/docs", produces = APPLICATION_JSON_VALUE)
public class TypeDocumentController {

    private TypeDocumentService typeDocumentService;

    public TypeDocumentController(TypeDocumentService typeDocumentService) {
        this.typeDocumentService = typeDocumentService;
    }

    @GetMapping
    public List<TypeDocumentView> handbookDocs() {
        return typeDocumentService.getAllTypeDocuments();
    }
}
