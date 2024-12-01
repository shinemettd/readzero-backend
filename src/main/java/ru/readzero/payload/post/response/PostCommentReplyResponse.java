package ru.readzero.payload.post.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PostCommentReplyResponse(
        Long parentCommentId,
        String content
) {
}
