package ru.readzero.entity.post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.readzero.entity.absctract.EditableBaseEntity;
import ru.readzero.entity.user.User;

import java.util.HashSet;
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
    Set<PostRate> postRates = new HashSet<>();

    @OneToMany(mappedBy = "post")
    Set<PostReport> postReports = new HashSet<>();

    @OneToMany(mappedBy = "post")
    Set<PostComment> postComments = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "AUTHOR_ID", nullable = false)
    User author;

    public void addComment(PostComment comment) {
        this.postComments.add(comment);
    }

}
