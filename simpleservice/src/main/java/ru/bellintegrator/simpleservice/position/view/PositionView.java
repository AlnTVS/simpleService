package ru.bellintegrator.simpleservice.position.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Date;

@ApiModel(description = "Должности")
public class PositionView {

    @NotEmpty
    @ApiModelProperty(value = "Уникальный идентификатор", example = "1")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String id;

    @Size(max = 255)
    @ApiModelProperty(value = "Название должности", example = "Операционист")
    public String name;

}
