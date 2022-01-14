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

    @NotEmpty(message = "Active status cannot be null")
    @NotNull
    @ApiModelProperty(value = "Id организации к которой относится офис", example = "1")
    public String orgId;

    @Size(max = 15)
    @ApiModelProperty(value = "Контактный номер", example = "+799912345678")
    public String phone;

    @Size(max = 127)
    @ApiModelProperty(value = "Название офиса", example = "Рога")
    public String name;

    @NotNull(message = "Active status cannot be null")
    @ApiModelProperty(value = "Статус активности", example = "true")
    public Boolean isActive;


}
