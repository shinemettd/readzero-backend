package ru.readzero.entity.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.readzero.entity.absctract.BaseEntity;

import java.util.Set;

@Data
@Entity
@Table(name = "USER_ROLES")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "USER_ROLE_SEQUENCE", sequenceName = "USER_ROLE_SEQ")
public class UserRole extends BaseEntity {

    @Column(name = "NAME")
    String name;

    @Column(name = "DESCRIPTION")
    String description;

    @OneToMany(mappedBy = "role")
    Set<User> user;

}
