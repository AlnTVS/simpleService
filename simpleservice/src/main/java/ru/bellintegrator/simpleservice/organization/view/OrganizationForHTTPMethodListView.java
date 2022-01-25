package ru.bellintegrator.simpleservice.organization.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * Используется для HTTP метода /list {@link ru.bellintegrator.simpleservice.organization.controller.OrganizationController}<code>.organizations()</code>
 * как фильтр на входе и представление результатов.
 *
 * @author Alntvs alntvs@yandex.ru https://github.com/AlnTVS
 * @version 1.0
 * @since 21.01.2021
 */
@ApiModel(description = "Информация по организациям")
public class OrganizationForHTTPMethodListView {


    /**
     * Поле <code>id</code> Организации
     */
    @NotEmpty
    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    public String id;

    /**
     * Поле <code>name</code> Организации
     */
    @Size(max = 127)
    @NotEmpty(message = "name cannot be null")
    @NotNull
    @ApiModelProperty(value = "Название компании", example = "ОАО \"Рога и Копыта\"")
    public String name;

    /**
     * Поле <code>inn</code> Организации
     */
    @Size(max = 12)
    @NotNull
    @ApiModelProperty(value = "Название компании", example = "123456789012")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String inn;

    /**
     * Поле <code>isActive</code> Организации
     */
    @NotNull(message = "Active status cannot be null")
    @ApiModelProperty(value = "Статус активности", example = "true")
    public Boolean isActive;

    @Override
    public String toString() {
        return "{id:" + id + ";name:" + name + ";isActive:" + isActive + "}";
    }
}
