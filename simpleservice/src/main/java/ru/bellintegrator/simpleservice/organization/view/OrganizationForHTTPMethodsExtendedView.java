package ru.bellintegrator.simpleservice.organization.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Используется для HTTP методов /id, /update, /save {@link ru.bellintegrator.simpleservice.organization.controller.OrganizationController}<code>.organisationById()</code>
 * ,<code>.updateOrganization()</code>
 * ,<code>.addNewOrganization()</code>
 * как фильтр на входе и представление результатов.
 *
 * @author Alntvs alntvs@yandex.ru https://github.com/AlnTVS
 * @version 1.0
 * @since 21.01.2021
 */
@ApiModel(description = "Полная информации по организациям")
public class OrganizationForHTTPMethodsExtendedView {

    /**
     * Поле <code>id</code> Организации
     */
    @NotEmpty
    @NotNull
    @ApiModelProperty(value = "Уникальный идентификатор", example = "1")
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
     * Поле <code>fullName</code> Организации
     */
    @Size(max = 255)
    @NotEmpty(message = "Fullname cannot be null")
    @NotNull
    @ApiModelProperty(value = "Полное название компании", example = "ОАО \"Рога и Копыта\"")
    public String fullName;

    /**
     * Поле <code>inn</code> Организации
     */
    @Size(max = 12)
    @NotEmpty(message = "inn cannot be null")
    @NotNull
    @ApiModelProperty(value = "Номер ИНН", example = "123456789012")
    public String inn;

    /**
     * Поле <code>kpp</code> Организации
     */
    @Size(max = 9)
    @NotEmpty(message = "kpp cannot be null")
    @NotNull
    @ApiModelProperty(value = "Номер КПП", example = "123456789")
    public String kpp;

    /**
     * Поле <code>address</code> Организации
     */
    @NotEmpty
    @NotNull
    @ApiModelProperty(value = "Адрес компании", example = "г.Москва, ул. Московская, д. 11")
    public String address;

    /**
     * Поле <code>phone</code> Организации
     */
    @Size(max = 15)
    @ApiModelProperty(value = "Контактный номер", example = "+799912345678")
    public String phone;

    /**
     * Поле <code>isActive</code> Организации
     */
    @NotNull(message = "Active status cannot be null")
    @ApiModelProperty(value = "Статус активности", example = "true")
    public Boolean isActive;

    @Override
    public String toString() {
        return "FullOrganizationView{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", inn='" + inn + '\'' +
                ", kpp='" + kpp + '\'' +
                ", addresses='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
