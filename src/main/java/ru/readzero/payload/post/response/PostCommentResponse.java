package ru.readzero.payload.post.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record PostCommentResponse(
        Long id,
        String content,
        LocalDateTime creationDate
) {
}
