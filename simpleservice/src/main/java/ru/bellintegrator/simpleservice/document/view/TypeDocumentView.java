package ru.bellintegrator.simpleservice.document.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.Size;

@ApiModel(description = "Информация по типам документов")
public class TypeDocumentView {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String id;

    @Size(max = 3)
    public String code;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(max = 127)
    public String type;
}
