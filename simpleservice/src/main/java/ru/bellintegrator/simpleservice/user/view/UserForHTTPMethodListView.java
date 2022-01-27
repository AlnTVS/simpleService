package ru.bellintegrator.simpleservice.user.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ru.bellintegrator.simpleservice.document.view.DocumentView;
import ru.bellintegrator.simpleservice.position.view.PositionView;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@ApiModel(description = "Информация по пользователям")
public class UserForHTTPMethodListView {

    @ApiModelProperty(value = "Уникальный идентификатор", hidden = true, example = "1")
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

    @Size(max = 63)
    @ApiModelProperty(value = "Фамилия", example = "Иванов")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String lastName;

    @Size(max = 15)
    @ApiModelProperty(value = "Должности", example = "Операционист")
    public Set<String> positions;

    @ApiModelProperty(value = "Код типа документ")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String docCode;

    @ApiModelProperty(value = "Код города")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String citizenshipCode;

}
