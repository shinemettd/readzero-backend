package ru.readzero.entity.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.readzero.entity.absctract.BaseEntity;

@Data
@Entity
@Table(name = "USERS")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "USERS_SEQUENCE", sequenceName = "USERS_SEQ")
public class User extends BaseEntity {

    @Column(name = "USERNAME", nullable = false)
    String username;

    @Column(name = "EMAIL", nullable = false)
    String email;

    @Column(name = "PASSWORD", nullable = false)
    String password;

    @Column(name = "IS_BLOCKED", nullable = false)
    boolean isBlocked;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", nullable = false)
    UserRole role;

    @OneToOne(mappedBy = "user")
    UserInfo userInfo;

}
