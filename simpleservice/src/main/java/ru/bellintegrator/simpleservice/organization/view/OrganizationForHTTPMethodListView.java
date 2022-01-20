package ru.bellintegrator.simpleservice.organization.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "Информация по организациям")
public class OrganizationForHTTPMethodListView {
    
    @NotEmpty
    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    public String id;

    @Size(max = 127)
    @NotEmpty(message = "name cannot be null")
    @NotNull
    @ApiModelProperty(value = "Название компании", example = "ОАО \"Рога и Копыта\"")
    public String name;

    @Size(max = 12)
    @NotNull
    @ApiModelProperty(value = "Название компании", example = "123456789012")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String inn;

    @NotNull(message = "Active status cannot be null")
    @ApiModelProperty(value = "Статус активности", example = "true")
    public Boolean isActive;

    @Override
    public String toString() {
        return "{id:" + id + ";name:" + name + ";isActive:" + isActive + "}";
    }
}
