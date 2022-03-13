package ru.bellintegrator.simpleservice.office.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.simpleservice.office.service.OfficeService;
import ru.bellintegrator.simpleservice.office.view.OfficeForHTTPMethodsView;
import ru.bellintegrator.simpleservice.office.view.OfficeForHTTPMethodListView;
import ru.bellintegrator.simpleservice.organization.service.OrganizationService;
import ru.bellintegrator.simpleservice.organization.view.OrganizationForHTTPMethodListView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
/**
 * OfficeController класс обрабатывающий REST запросы по адрессу api/office.
 * Обрабатывает запросы непосредственно относящиеся к организациям.
 *
 * @author Alntvs alntvs@yandex.ru https://github.com/AlnTVS
 * @version 1.1
 * @since 13.03.2022
 * */
@Api(value = "OfficeController", description = "Управление информацией об офисах")
@RestController
@RequestMapping(value = "/office", produces = APPLICATION_JSON_VALUE)
public class OfficeController {

    public OfficeService officeService;

    /**
     * Создает экземпляр этого класса.
     * Используется аннотация <i>@Autowired</i>, для автозаполнения инъекцией.
     *
     * @param officeService реализация интерфейса {@link OfficeService}.
     * */
    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    /**
     * Возвращает список офисов. Возвращает список всех существующих офисов при отсутствии фильтра на входе (тело post запроса пустое).
     * Если в запросе установить фильтр, будут возвращены только те офисы, которые удовлетворяют заданным в фильтре параметрам.
     * Вызывается соответствующий метод экземпляра класса {@link OfficeService}.
     *
     * @param officeForHTTPMethodListView используется как фильтр параметров. Является объектом класса {@link OrganizationForHTTPMethodListView}.
     * Может быть <code>null</code>.
     * @return <code>List&lt;OfficeForHTTPMethodListView&gt;</code> список сокращенных views для организаций,
     * удовлетворяющих фильтру <code>officeForHTTPMethodListView</code> на входе}
     */
    @ApiOperation(value = "Получить список всех офисов", httpMethod = "POST")
    @PostMapping("/list")
    public List<OfficeForHTTPMethodListView> offices(@RequestBody(required = false) OfficeForHTTPMethodListView officeForHTTPMethodListView) {
        List<OfficeForHTTPMethodListView> officeForHTTPMethodListViews = null;
        if (officeForHTTPMethodListView != null) {
            officeForHTTPMethodListViews = officeService.offices(officeForHTTPMethodListView);
        } else {
            officeForHTTPMethodListViews = officeService.offices();
        }
        return officeForHTTPMethodListViews;
    }

    /**
     * Возвращает подробные данные по офису с переданным id. Вызывает соответствующий метод экземпляра класса {@link OfficeService}.
     *
     * @param id офис который будет возвращен.
     * @return <code>OfficeForHTTPMethodsView</code> если организация с таким id существует.
     * Возвращается расширенный view для организаций, с подробной информацией.
     */
    @ApiOperation(value = "Получить офис по id", httpMethod = "GET")
    @GetMapping("/{id}")
    public OfficeForHTTPMethodsView officeById(@PathVariable Long id) {
        return officeService.officeById(id);
    }

    /**
     * Вызывается соответствующий метод экземпляра класса {@link OfficeService}.
     *
     * @param officeForHTTPMethodsView расширенный view организации,
     * должен представлять вид организации к которому ее требуется привести.
     */
    @ApiOperation(value = "Обновить информацию по офису", httpMethod = "POST")
    @PostMapping("/update")
    public void updateOffice(@RequestBody OfficeForHTTPMethodsView officeForHTTPMethodsView) {
        officeService.updateOffice(officeForHTTPMethodsView);
    }

    /**
     * Вызывается соответствующий метод экземпляра класса {@link OfficeService}.
     *
     * @param officeForHTTPMethodsView расширенный view организации,
     * должен представлять вид организации который необходимо добавить в сервис.
     */
    @ApiOperation(value = "Добавить новый офис", httpMethod = "POST")
    @PostMapping("/save")
    public void addNewOffice(@RequestBody OfficeForHTTPMethodsView officeForHTTPMethodsView) {
        officeService.addNewOffice(officeForHTTPMethodsView);
    }
}
