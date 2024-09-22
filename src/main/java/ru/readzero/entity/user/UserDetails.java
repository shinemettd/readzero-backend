package ru.readzero.entity.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.readzero.entity.Cult;
import ru.readzero.entity.absctract.EditableBaseEntity;

@Data
@Entity
@Table(name = "USER_DETAILS")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "USER_DETAILS_SEQUENCE", sequenceName = "USER_DETAILS_SEQ")
public class UserDetails extends EditableBaseEntity {

    @Column(name = "AVATAR_URL")
    String avatarUrl;

    @Column(name = "USER_DESCRIPTION")
    String description;

    @Column(name = "BLOCK_REASON")
    String blockReason;

    @ManyToOne
    @JoinColumn(name = "CULT_ID")
    Cult cult;

}
