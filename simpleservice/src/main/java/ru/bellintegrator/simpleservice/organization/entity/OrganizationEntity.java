package ru.bellintegrator.simpleservice.organization.entity;

import lombok.*;
import ru.bellintegrator.simpleservice.address.entity.AddressEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * OrganizationEntity сущность <code>Entity</code> связанная с таблицей organization в БД.
 *
 * @author Alntvs alntvs@yandex.ru https://github.com/AlnTVS
 * @version 1.0
 * @since 21.01.2021
 */
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "organization")
public class OrganizationEntity {

    /**
     * Поле <code>id</code> соответствует столбцу <code>id</code> в таблице <code>organization</code> в БД.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Поле <code>id</code> соответствует столбцу <code>id</code> в таблице <code>organization</code> в БД.
     * Установленное ограничение длинны строки ровняется 127 символам и не может быть <code>null</code>, что соответствует ограничению в БД.
     */
    @Column(name = "name", length = 127, nullable = false)
    private String name;

    /**
     * Поле <code>fullName</code> соответствует столбцу <code>full_name</code> в таблице <code>organization</code> в БД.
     * Установленное ограничение длинны строки ровняется 255 символам и не может быть <code>null</code>, что соответствует ограничению в БД.
     */
    @Column(name = "full_name", length = 255, nullable = false)
    private String fullName;

    /**
     * Поле <code>inn</code> соответствует столбцу <code>inn</code> в таблице <code>organization</code> в БД.
     * Установленное ограничение длинны строки ровняется 12 символам и не может быть <code>null</code>, что соответствует ограничению в БД.
     */
    @Column(name = "inn", length = 12, nullable = false)
    private String inn;

    /**
     * Поле <code>kpp</code> соответствует столбцу <code>kpp</code> в таблице <code>organization</code> в БД.
     * Установленное ограничение длинны строки ровняется 9 символам и не может быть <code>null</code>, что соответствует ограничению в БД.
     */
    @Column(name = "kpp", length = 9, nullable = false)
    private String kpp;

    /**
     * Поле <code>phone</code> соответствует столбцу <code>phone</code> в таблице <code>organization</code> в БД.
     * Установленное ограничение длинны строки ровняется 15 символам, что соответствует ограничению в БД.
     */
    @Column(name = "phone", length = 15)
    private String phone;

    /**
     * Поле <code>isActive</code> соответствует столбцу <code>is_active</code> в таблице <code>organization</code> в БД.
     */
    @Column(name = "is_active")
    private Boolean isActive;

    /**
     * Используется для <i>односторонней</i> связи <code>ManyToOne</code> с сущностью {@link AddressEntity}.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

}
