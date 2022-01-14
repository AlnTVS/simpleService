package ru.bellintegrator.simpleservice.office.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "Информация по офисам")
public class OfficeView {

    @NotEmpty
    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    public String id;

    @Size(max = 127)
    @NotEmpty(message = "name cannot be null")
    @NotNull
    @ApiModelProperty(value = "Название офиса", example = "ОАО \"Рога и Копыта\"")
    public String name;

    @NotNull(message = "Active status cannot be null")
    @ApiModelProperty(value = "Статус активности", example = "true")
    public Boolean isActive;


}
