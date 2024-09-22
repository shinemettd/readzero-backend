package ru.readzero.entity.absctract;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENTITY_SEQUENCE_GENERATOR")
    Long id;

    @CreatedDate
    @Column(name = "CREATION_DATE", updatable = false)
    LocalDateTime creationDate;

    @Column(name = "IS_DELETED")
    boolean isDeleted;

    @Column(name = "DELETE_DATE")
    LocalDateTime deleteDate;

}
