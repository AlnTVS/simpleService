package ru.bellintegrator.simpleservice.organization.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ru.bellintegrator.simpleservice.address.view.AddressView;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@ApiModel(description = "Полная информации по организациям")
public class FullOrganizationView {

    @NotEmpty
    @NotNull
    @ApiModelProperty(value = "Уникальный идентификатор", example = "1")
    public String id;

    @Size(max = 127)
    @NotEmpty(message = "name cannot be null")
    @NotNull
    @ApiModelProperty(value = "Название компании", example = "ОАО \"Рога и Копыта\"")
    public String name;

    @Size(max = 255)
    @NotEmpty(message = "Fullname cannot be null")
    @NotNull
    @ApiModelProperty(value = "Полное название компании", example = "ОАО \"Рога и Копыта\"")
    public String fullName;

    @Size(max = 12)
    @NotEmpty(message = "inn cannot be null")
    @NotNull
    @ApiModelProperty(value = "Номер ИНН", example = "123456789012")
    public String inn;

    @Size(max = 9)
    @NotEmpty(message = "kpp cannot be null")
    @NotNull
    @ApiModelProperty(value = "Номер КПП", example = "123456789")
    public String kpp;

    @NotEmpty
    @NotNull
    @ApiModelProperty(value = "Адресс компании", example = "г.Москва, ул. Московская, д. 11")
    public AddressView address;

    @Size(max = 15)
    @ApiModelProperty(value = "Контактный номер", example = "+799912345678")
    public String phone;

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
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
