package ru.readzero.payload.post.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PostCommentReplyRequest(
        Long parentCommentId,
        String content
) {
}
