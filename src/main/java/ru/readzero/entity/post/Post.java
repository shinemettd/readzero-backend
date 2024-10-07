package ru.readzero.entity.post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.readzero.entity.absctract.EditableBaseEntity;

import java.util.Set;

@Data
@Entity
@Table(name = "POSTS")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "POST_SEQUENCE", sequenceName = "POST_SEQ")
public class Post extends EditableBaseEntity {

    @Column(name = "TITLE", nullable = false)
    String title;

    @Lob
    @Column(name = "CONTENT", nullable = false)
    String content;

    @OneToMany(mappedBy = "post")
    Set<PostRate> postRates;

    @OneToMany(mappedBy = "post")
    Set<PostReport> postReports;

    @OneToMany(mappedBy = "post")
    Set<PostComment> postComments;

//    @ManyToOne
//    @JoinColumn(name = "POST_ID")
//    Set<PostComment> comments; //пока хз как делать, можно будет разделить на разные комменты, либо единые

    //todo комменты, теги, реакции/оценка
}
