package ru.bellintegrator.simpleservice.organization.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.simpleservice.organization.service.OrganizationService;
import ru.bellintegrator.simpleservice.organization.view.OrganizationForHTTPMethodsExtendedView;
import ru.bellintegrator.simpleservice.organization.view.OrganizationForHTTPMethodListView;

import java.lang.annotation.Documented;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
/**
 * OrganizationController класс обрабатывающий REST запросы по адрессу api/organization.
 * Обрабатывает запроссы непосредствено относящиеся к организациям.
 *
 * @author Alntvs alntvs@yandex.ru https://github.com/AlnTVS
 * @version 1.0
 * @since 21.01.2021
 * */
@Api(value = "OrganizationController", description = "Управление информацией об организациях")
@RestController
@RequestMapping(value = "/organization", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationService organizationService;

    /**
     * Создает экземпляр этого класса.
     * Используется аннотация <i>@Autowired</i>, для автозаполнения инъекцией.
     *
     * @param organizationService реализация интерфейса {@link OrganizationService}.
     * */
    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }


    /**
     * Возвращает список организаций. Возвращает список всех существующих организаций при отсутствии фильтра на входе (тело post запроса пустое).
     * Если в запросе установить фильтр, будут возвращены только те организации, которые удовлетворяют заданным в фильтре параметрам.
     * Вызывается соответствующий метод экземпляра класса {@link OrganizationService}.
     *
     * @param organization используется как фильтр параметров. Является объектом класса {@link OrganizationForHTTPMethodListView}.
     * Может быть <code>null</code>.
     * @return <code>List&lt;OrganizationForHTTPMethodListView&gt;</code> список сокращенных views для организаций,
     * удовлетворяющих фильтру <code>organization</code> на входе}
     */
    @ApiOperation(value = "Получить список всех организаций", httpMethod = "POST")
    @PostMapping("/list")
    public List<OrganizationForHTTPMethodListView> organizations(@RequestBody(required=false) OrganizationForHTTPMethodListView organization) {
        List<OrganizationForHTTPMethodListView> organizationForHTTPMethodListViewList = null;
        if (organization != null) {
            organizationForHTTPMethodListViewList = organizationService.organizations(organization);
        } else
        {
            organizationForHTTPMethodListViewList = organizationService.organizations();
        }
        return organizationForHTTPMethodListViewList;
    }

    /**
     * Возвращает подробные данные по организации с переданным id. Вызывает соответствующий метод экземпляра класса {@link OrganizationService}.
     *
     * @param id организации которая будет возвращена.
     * @return <code>OrganizationForHTTPMethodsExtendedView</code> если организация с таким id существует.
     * Возвращается расширенный view для организаций, с подробной информацией.
     */
    @ApiOperation(value = "Получить организацию по id", httpMethod = "GET")
    @GetMapping("/{id}")
    public OrganizationForHTTPMethodsExtendedView organisationById(@PathVariable Long id) {
        return organizationService.organisationById(id);
    }

    /**
     * Вызывается соответствующий метод экземпляра класса {@link OrganizationService}.
     *
     * @param organizationForHTTPMethodsExtendedView расширенный view организации,
     * должен представлять вид организации к которому ее требуется привести.
     */
    @ApiOperation(value = "Обновить информацию об организации", httpMethod = "POST")
    @PostMapping("/update")
    public void updateOrganization(@RequestBody OrganizationForHTTPMethodsExtendedView organizationForHTTPMethodsExtendedView) {
        organizationService.updateOrganization(organizationForHTTPMethodsExtendedView);
        return;
    }

    /**
     * Вызывается соответствующий метод экземпляра класса {@link OrganizationService}.
     *
     * @param organizationForHTTPMethodsExtendedView расширенный view организации,
     * должен представлять вид организации который необходимо добавить в сервис.
     */
    @ApiOperation(value = "Добавить новую организацию", httpMethod = "POST")
    @PostMapping("/save")
    public void addNewOrganization(@RequestBody OrganizationForHTTPMethodsExtendedView organizationForHTTPMethodsExtendedView) {
        organizationService.addNewOrganization(organizationForHTTPMethodsExtendedView);
        return;
    }
}