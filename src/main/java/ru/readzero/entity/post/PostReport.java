package ru.readzero.entity.post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.readzero.entity.absctract.ReportableBaseEntity;

@Data
@Entity
@Table(name = "POST_REPORTS")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "POST_REPORTS_SEQUENCE", sequenceName = "POST_REPORTS_SEQ")
public class PostReport extends ReportableBaseEntity {

    @ManyToOne
    @JoinColumn(name = "POST_ID", nullable = false)
    Post post;

}
