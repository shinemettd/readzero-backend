package ru.readzero.entity.post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.readzero.entity.absctract.CommentableBaseEntity;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "POST_COMMENTS")
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "POST_COMMENTS_SEQUENCE", sequenceName = "POST_COMMENTS_SEQ")
public class PostComment extends CommentableBaseEntity {

    @ManyToOne
    @JoinColumn(name = "POST_ID", nullable = false)
    Post post;

    @Column(name = "CONTENT", nullable = false)
    String content;

    @ManyToOne
    @JoinColumn(name = "PARENT_COMMENT_ID")
    PostComment parentComment;

    @OneToMany(mappedBy = "parentComment", fetch = FetchType.EAGER)
    Set<PostComment> replies = new HashSet<>();

    public void addReply(PostComment reply) {
        this.replies.add(reply);
    }

}
