package ru.readzero.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.readzero.entity.post.Post;
import ru.readzero.entity.post.PostComment;
import ru.readzero.entity.user.User;
import ru.readzero.exception.NotFoundException;
import ru.readzero.mapper.post.PostCommentMapper;
import ru.readzero.mapper.post.PostMapper;
import ru.readzero.payload.post.PostCommentDTO;
import ru.readzero.payload.post.request.PostCommentRequest;
import ru.readzero.payload.post.request.PostRequest;
import ru.readzero.payload.post.response.PostCommentReplyResponse;
import ru.readzero.payload.post.response.PostCommentResponse;
import ru.readzero.payload.post.response.PostResponse;
import ru.readzero.repository.PostCommentRepository;
import ru.readzero.repository.PostRepository;
import ru.readzero.service.PostService;
import ru.readzero.util.UserUtil;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostServiceImpl implements PostService {

    PostRepository postRepository;
    PostCommentRepository postCommentRepository;

    PostMapper postMapper;
    PostCommentMapper postCommentMapper;

    public Page<PostResponse> getPosts(Pageable pageable) {
        return postRepository.findAll(pageable).map(postMapper::toResponse);
    }

    @Override
    public PostResponse getPost(Long id) {
        return postRepository.findById(id).map(postMapper::toResponse)
                .orElseThrow(() -> new NotFoundException("User not found with id " + id));
    }

    @Override
    public PostResponse create(PostRequest postRequest) {
        Post post = new Post();
        post.setTitle(postRequest.title());
        post.setContent(postRequest.content());
        User currentUser = UserUtil.getCurrentUser();
        post.setAuthor(currentUser);

        post = postRepository.save(post);

        return postMapper.toResponse(post);
    }

    @Override
    public PostResponse update(Long id, PostRequest postRequest) {
        Post post = postRepository.findById(id).orElseThrow();
        User currentUser = UserUtil.getCurrentUser();
        if (!currentUser.getUsername().equals(post.getAuthor().getUsername())) {
            throw new IllegalArgumentException("You dont have permission to change other posts");
        }
        post.setTitle(postRequest.title());
        post.setContent(postRequest.content());

        post = postRepository.save(post);

        return postMapper.toResponse(post);
    }

    @Override
    public PostResponse deleteById(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return postMapper.toResponse(post);
    }

    @Override
    public PostCommentDTO getComment(Long id) {
        PostComment postComment = postCommentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Comment not found with id " + id));
        return postCommentMapper.toDTO(postComment);
    }

    @Override
    public Page<PostCommentDTO> getComments(Pageable pageable) {
        return postCommentRepository.findAll(pageable).map(postCommentMapper::toDTO);
    }

    @Override
    public Page<PostCommentDTO> getCommentsByPostId(Pageable pageable, Long postId) {
        return postCommentRepository.findByPostId(postId, pageable).map(postCommentMapper::toDTO);
    }

    @Override
    public PostCommentResponse createComment(Long postId, PostCommentRequest postCommentRequest) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post not found with id " + postId));

        PostComment postComment = postCommentMapper.toEntity(postCommentRequest);
        postComment.setPost(post);
        postComment = postCommentRepository.save(postComment);

        post.addComment(postComment);
        postRepository.save(post);

        return postCommentMapper.toResponse(postComment);
    }

    @Override
    public PostCommentReplyResponse replyToComment(Long commentId, PostCommentRequest postCommentRequest) {
        PostComment parentComment = postCommentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment not found with id " + commentId));

        PostComment reply = postCommentMapper.toEntity(postCommentRequest);
        reply.setPost(parentComment.getPost());
        reply = postCommentRepository.save(reply);

        parentComment.addReply(reply);
        postCommentRepository.save(parentComment);

        return postCommentMapper.toReplyResponse(reply);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        PostComment comment = postCommentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Comment not found with id " + commentId));
        comment.setDeleteDate(LocalDateTime.now());
        comment.setDeleted(true);
    }

}
