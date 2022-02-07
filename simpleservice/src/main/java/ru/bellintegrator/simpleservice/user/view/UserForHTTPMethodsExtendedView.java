package ru.bellintegrator.simpleservice.user.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@ApiModel(description = "Информация по пользователям")
public class UserForHTTPMethodsExtendedView {

    @ApiModelProperty(value = "Уникальный идентификатор", example = "1")
    public String id;

    @NotEmpty
    @NotNull
    @ApiModelProperty(value = "Id офиса к которму относится пользователь", example = "1")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String officeId;

    @Size(max = 63)
    @NotEmpty
    @ApiModelProperty(value = "Имя", example = "Иван")
    public String firstName;

    @Size(max = 63)
    @ApiModelProperty(value = "Отчество", example = "Иванович")
    public String secondName;

    @Size(max = 63)
    @ApiModelProperty(value = "Среднее/второе имя", example = "Вася")
    public String middleName;

    @Size(max = 15)
    @ApiModelProperty(value = "Должности", example = "Операционист")
    public Set<String> positions;

    @Size(max = 15)
    @ApiModelProperty(value = "Номер телефона", example = "+79091234567")
    public String phone;

    @Size(max = 127)
    @ApiModelProperty(value = "Наименование документа")
    public String docName;

    @Size(max = 63)
    @ApiModelProperty(value = "Номер документа")
    public String docNumber;

    @Size(max = 127)
    @ApiModelProperty(value = "Дата документа")
    @Temporal(TemporalType.DATE)
    public Date docDate;

    @Size(max = 127)
    @ApiModelProperty(value = "Название города")
    public String citizenshipName;

    @Size(max = 3)
    @ApiModelProperty(value = "Код города")
    public String citizenshipCode;

}