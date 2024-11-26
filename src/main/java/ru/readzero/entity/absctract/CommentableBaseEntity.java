package ru.readzero.entity.absctract;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.readzero.entity.user.UserInfo;

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class CommentableBaseEntity extends EditableBaseEntity {

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID", nullable = false)
    UserInfo commentAuthor;

}
