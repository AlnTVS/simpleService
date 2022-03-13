package ru.bellintegrator.simpleservice.office.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.bellintegrator.simpleservice.address.entity.AddressEntity;
import ru.bellintegrator.simpleservice.user.entity.UserEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * OfficeEntity сущность <code>Entity</code> связанная с таблицей office в БД.
 *
 * @author Alntvs alntvs@yandex.ru https://github.com/AlnTVS
 * @version 1.1
 * @since 13.03.2022
 */
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "office")
public class OfficeEntity {

    /**
     * Поле <code>id</code> соответствует столбцу <code>id</code> в таблице <code>office</code> в БД.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Поле <code>orgId</code> соответствует столбцу <code>organization_id</code> в таблице <code>office</code> в БД.
     */
    //TODO not correct relationship between organization and office entity.
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
//    @JoinColumn(name = "organization_id")
    @Column(name = "organization_id", nullable = false)
    private Long orgId;
    /**
     * Поле <code>name</code> соответствует столбцу <code>name</code> в таблице <code>office</code> в БД.
     */
    @Column(name = "name", length = 255)
    private String name;

    /**
     * Поле <code>phone</code> соответствует столбцу <code>phone</code> в таблице <code>office</code> в БД.
     */
    @Column(name = "phone", length = 15)
    private String phone;

    /**
     * Поле <code>isActive</code> соответствует столбцу <code>is_active</code> в таблице <code>office</code> в БД.
     */
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    /**
     * Поле <code>address</code> соответствует столбцу <code>address_id</code> в таблице <code>office</code> в БД.
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

}
