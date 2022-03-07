package ru.bellintegrator.simpleservice.office.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "Информация по офисам")
public class OfficeForHTTPMethodListView {

    @NotEmpty
    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    public String id;

    @NotEmpty()
    @NotNull
    @ApiModelProperty(value = "Id организации к которой относится офис", example = "1")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String orgId;

    @Size(max = 255)
    @ApiModelProperty(value = "Название офиса", example = "Рога")
    public String name;

    @Size(max = 15)
    @ApiModelProperty(value = "Контактный номер", example = "+799912345678")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String phone;

    @NotNull(message = "Active status cannot be null")
    @ApiModelProperty(value = "Статус активности", example = "true")
    public Boolean isActive;

}
