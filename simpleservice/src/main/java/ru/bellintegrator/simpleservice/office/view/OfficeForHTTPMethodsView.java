package ru.bellintegrator.simpleservice.office.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ru.bellintegrator.simpleservice.address.view.AddressView;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Используется для HTTP методов /id, /update, /save {@link ru.bellintegrator.simpleservice.office.controller.OfficeController}<code>.officeById()</code>
 * ,<code>.updateOffice()</code>
 * ,<code>.addNewOffice()</code>
 * как фильтр на входе и представление результатов.
 *
 * @author Alntvs alntvs@yandex.ru https://github.com/AlnTVS
 * @version 1.1
 * @since 13.03.2022
 */
@ApiModel(description = "Полная информация по офисам")
public class OfficeForHTTPMethodsView {

    /**
     * Поле <code>id</code> офиса
     */
    @NotEmpty
    @NotNull
    @ApiModelProperty(value = "Уникальный идентификатор", example = "1")
    public String id;

    /**
     * Поле <code>orgId</code> офиса
     */
    @ApiModelProperty(value = "id компании", example = "2")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String orgId;

    /**
     * Поле <code>name</code> офиса
     */
    @Size(max = 255)
    @ApiModelProperty(value = "Название офиса", example = "Рога")
    public String name;

    /**
     * Поле <code>address</code> офиса
     */
    @NotNull
    @ApiModelProperty(value = "Адрес офиса", example = "г.Москва, ул. Московская, д. 11")
    public String address;

    /**
     * Поле <code>phone</code> офиса
     */
    @Size(max = 15)
    @ApiModelProperty(value = "Контактный номер", example = "+799912345678")
    public String phone;

    /**
     * Поле <code>isActive</code> офиса
     */
    @NotNull(message = "Active status cannot be null")
    @ApiModelProperty(value = "Статус активности", example = "true")
    public Boolean isActive;
}
