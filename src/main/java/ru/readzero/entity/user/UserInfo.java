package ru.readzero.entity.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.readzero.entity.Cult;
import ru.readzero.entity.absctract.EditableBaseEntity;
import ru.readzero.entity.post.PostRate;
import ru.readzero.entity.post.PostReport;

import java.util.Set;

@Data
@Entity
@Table(name = "USER_INFOS")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "USER_INFO_SEQUENCE", sequenceName = "USER_INFO_SEQ")
public class UserInfo extends EditableBaseEntity {

    @MapsId
    @OneToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    User user;

    @Column(name = "AVATAR_URL")
    String avatarUrl;

    @Column(name = "USER_DESCRIPTION")
    String description;

    @Column(name = "BLOCK_REASON")
    String blockReason;

    @ManyToOne
    @JoinColumn(name = "CULT_ID")
    Cult cult;

    @OneToMany(mappedBy = "user")
    Set<PostRate> postRates;

    @OneToMany(mappedBy = "reportAuthor")
    Set<PostReport> postReports;

}
