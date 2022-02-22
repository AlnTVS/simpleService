package ru.bellintegrator.simpleservice.document.service;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.simpleservice.document.dao.TypeDocumentDao;
import ru.bellintegrator.simpleservice.document.view.TypeDocumentView;
import ru.bellintegrator.simpleservice.mapper.MapperFacade;

import java.util.List;

@Service
public class TypeDocumentServiceImpl implements TypeDocumentService{

    private TypeDocumentDao typeDocumentDao;
    private MapperFacade mapperFacade;

    public TypeDocumentServiceImpl(TypeDocumentDao typeDocumentDao, MapperFacade mapperFacade) {
        this.typeDocumentDao = typeDocumentDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public List<TypeDocumentView> getAllTypeDocuments() {
        return mapperFacade.mapAsList(typeDocumentDao.loadAllTypeDocuments(),TypeDocumentView.class);
    }
}
