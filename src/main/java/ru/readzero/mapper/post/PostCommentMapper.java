package ru.readzero.mapper.post;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.readzero.entity.post.PostComment;
import ru.readzero.payload.post.PostCommentDTO;
import ru.readzero.payload.post.request.PostCommentReplyRequest;
import ru.readzero.payload.post.request.PostCommentRequest;
import ru.readzero.payload.post.response.PostCommentReplyResponse;
import ru.readzero.payload.post.response.PostCommentResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostCommentMapper {
    PostComment toEntity(PostCommentRequest postCommentRequest);
    PostCommentResponse toResponse(PostComment postComment);

    PostComment toReplyEntity(PostCommentReplyRequest postCommentReplyRequest);
    PostCommentReplyResponse toReplyResponse(PostComment postComment);

    PostCommentDTO toDTO(PostComment postComment);
}
