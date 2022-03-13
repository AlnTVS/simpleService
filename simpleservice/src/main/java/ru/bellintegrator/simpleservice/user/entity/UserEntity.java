package ru.bellintegrator.simpleservice.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SortNatural;
import ru.bellintegrator.simpleservice.citizenship.entity.CitizenshipEntity;
import ru.bellintegrator.simpleservice.document.entity.DocumentEntity;
import ru.bellintegrator.simpleservice.office.entity.OfficeEntity;
import ru.bellintegrator.simpleservice.position.entity.PositionEntity;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private OfficeEntity office;

    @Column(name = "first_name", length = 63, nullable = false)
    private String firstName;

    @Column(name = "second_name", length = 63)
    private String secondName;

    @Column(name = "middle_name", length = 63)
    private String middleName;

    @Column(name = "last_name", length = 63)
    private String lastName;

    @OneToOne(mappedBy = "user", optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DocumentEntity document;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name = "user_position",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "position_id")
    )
    private Set<PositionEntity> positions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenship_id")
    private CitizenshipEntity citizenship;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "is_active")
    private Boolean isActive;

    //    public void addPosition(PositionEntity position) {
//        positions.add(position);
//        position.getUsers().add(this);
//    }
//    public void removePosition(PositionEntity position) {
//        positions.remove(position);
//        position.getUsers().remove(this);
//    }


}
