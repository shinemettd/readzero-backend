package ru.readzero.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.readzero.payload.post.PostCommentDTO;
import ru.readzero.payload.post.request.PostCommentRequest;
import ru.readzero.payload.post.request.PostRequest;
import ru.readzero.payload.post.response.PostCommentReplyResponse;
import ru.readzero.payload.post.response.PostCommentResponse;
import ru.readzero.payload.post.response.PostResponse;

public interface PostService {
    Page<PostResponse> getPosts(Pageable pageable);
    PostResponse getPost(Long id);
    PostResponse create(PostRequest postRequest);
    PostResponse update(Long id, PostRequest postRequest);
    PostResponse deleteById(Long id);

    PostCommentDTO getComment(Long id);
    Page<PostCommentDTO> getComments(Pageable pageable);
    Page<PostCommentDTO> getCommentsByPostId(Pageable pageable, Long postId);
    PostCommentResponse createComment(Long postId, PostCommentRequest postCommentRequest);
    PostCommentReplyResponse replyToComment(Long commentId, PostCommentRequest postCommentRequest);
    void deleteComment(Long commentId);
}
