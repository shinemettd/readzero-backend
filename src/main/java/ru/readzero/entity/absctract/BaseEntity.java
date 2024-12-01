package ru.readzero.entity.absctract;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Table(
        indexes = @Index(name = "index_creation_date", columnList = "CREATION_DATE")
)
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @CreatedDate
    @Column(name = "CREATION_DATE", updatable = false)
    LocalDateTime creationDate;

    @Column(name = "IS_DELETED")
    boolean isDeleted;

    @Column(name = "DELETE_DATE")
    LocalDateTime deleteDate;

}
