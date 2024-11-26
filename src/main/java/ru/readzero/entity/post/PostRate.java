package ru.readzero.entity.post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.readzero.entity.absctract.EditableBaseEntity;
import ru.readzero.entity.user.UserInfo;
import ru.readzero.enums.Reaction;

@Data
@Entity
@Table(name = "POST_RATES")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "POST_RATE_SEQUENCE", sequenceName = "POST_RATE_SEQ")
public class PostRate extends EditableBaseEntity {

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    UserInfo user;

    @ManyToOne
    @JoinColumn(name = "POST_ID", nullable = false)
    Post post;

    @Column(name = "REACTION", nullable = false)
    @Enumerated(EnumType.STRING)
    Reaction reaction;

}
