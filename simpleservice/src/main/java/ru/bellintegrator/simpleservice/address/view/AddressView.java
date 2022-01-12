package ru.bellintegrator.simpleservice.address.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "Адреса")
public class AddressView {
    
    @NotEmpty
    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String id;

    @Size(max = 511)
    @NotEmpty(message = "name cannot be null")
    @NotNull
    @ApiModelProperty(value = "Адрес", example = "г.Москва, ул. Весна, д. 22")
    public String address;

    @Override
    public String toString() {
        return "AddressView{" +
                "address='" + address + '\'' +
                '}';
    }
}
