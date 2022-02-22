package ru.bellintegrator.simpleservice.citizenship.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.Size;

@ApiModel(description = "Информация по странам")
public class CitizenshipView {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String id;

    @Size(max = 127)
    public String name;

    @Size(max = 3)
    public String code;

}
