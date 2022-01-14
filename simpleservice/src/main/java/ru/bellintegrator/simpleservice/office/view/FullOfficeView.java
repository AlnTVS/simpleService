package ru.bellintegrator.simpleservice.office.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ru.bellintegrator.simpleservice.address.view.AddressView;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "Полная информация по офисам")
public class FullOfficeView {

    @NotEmpty
    @NotNull
    @ApiModelProperty(value = "Уникальный идентификатор", example = "1")
    public String id;

    @Size(max = 127)
    @ApiModelProperty(value = "Название офиса", example = "Рога")
    public String name;

    @NotNull
    @ApiModelProperty(value = "Адрес офиса", example = "г.Москва, ул. Московская, д. 11")
    public AddressView address;

    @Size(max = 15)
    @ApiModelProperty(value = "Контактный номер", example = "+799912345678")
    public String phone;

    @NotNull(message = "Active status cannot be null")
    @ApiModelProperty(value = "Статус активности", example = "true")
    public Boolean isActive;
}
