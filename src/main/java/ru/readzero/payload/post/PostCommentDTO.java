package ru.readzero.payload.post;

import java.util.List;

public record PostCommentDTO(
        Long postId,
        String content,
        PostCommentDTO parentComment,
        List<PostCommentDTO> replies
) {
}
