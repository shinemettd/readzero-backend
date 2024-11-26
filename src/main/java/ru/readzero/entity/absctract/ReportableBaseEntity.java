package ru.readzero.entity.absctract;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.readzero.entity.post.Post;
import ru.readzero.entity.user.UserInfo;
import ru.readzero.enums.ReportDecision;
import ru.readzero.enums.ReportType;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class ReportableBaseEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID", nullable = false)
    UserInfo reportAuthor;

    @Column(name = "REPORT_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    ReportType reportType;

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "DECISION")
    @Enumerated(EnumType.STRING)
    ReportDecision reportDecision;

}
