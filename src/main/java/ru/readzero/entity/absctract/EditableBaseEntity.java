package ru.readzero.entity.absctract;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class EditableBaseEntity extends BaseEntity {

    @Column(name = "IS_EDITED")
    boolean isEdited;

    @Column(name = "EDIT_DATE")
    LocalDateTime editDate;

}
