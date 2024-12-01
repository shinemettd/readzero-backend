package ru.readzero.entity.session;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.readzero.entity.absctract.BaseEntity;
import ru.readzero.entity.user.User;
import ru.readzero.enums.session.DeviceType;
import ru.readzero.enums.session.SessionActionType;
import ru.readzero.enums.session.SystemType;

@Getter
@Setter
@Entity
@Table(name = "SESSION")
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "SESSION_SEQUENCE", sequenceName = "SESSION_SEQ")
public class Session extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "USER_ID", updatable = false)
    User user;

    @Column(name = "IP", nullable = false, updatable = false)
    String ip;

    @Enumerated(EnumType.STRING)
    @Column(name = "SYSTEM_TYPE")
    SystemType system;

    @Enumerated(EnumType.STRING)
    @Column(name = "DEVICE_TYPE")
    DeviceType deviceType;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACTION_TYPE", updatable = false)
    SessionActionType actionType;

    @Column(name = "LOCALE", updatable = false)
    String locale;


}
