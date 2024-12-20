package ru.readzero.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.readzero.entity.absctract.BaseEntity;
import ru.readzero.entity.user.UserInfo;

import java.util.Set;

@Data
@Entity
@Table(name = "CULT")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "CULT_SEQUENCE", sequenceName = "CULT_SEQ")
public class Cult extends BaseEntity { //группы, мб не нужно будет и выпилю

    @Column(name = "NAME")
    String name;

    @Column(name = "DESCRIPTION")
    String description;

    @OneToMany(mappedBy = "cult")
    Set<UserInfo> user;

}
