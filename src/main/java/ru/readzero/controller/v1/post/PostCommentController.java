package ru.readzero.controller.v1.post;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.readzero.payload.post.PostCommentDTO;
import ru.readzero.payload.post.request.PostCommentRequest;
import ru.readzero.payload.post.response.PostCommentReplyResponse;
import ru.readzero.payload.post.response.PostCommentResponse;
import ru.readzero.service.PostService;

@RestController
@RequestMapping("/v1/posts/comment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostCommentController {

    PostService postService;

    @GetMapping("/all")
    public ResponseEntity<Page<PostCommentDTO>> getAll(Pageable pageable) {
        return new ResponseEntity<>(postService.getComments(pageable), HttpStatus.OK);
    }

    @GetMapping("/all/{postId}")
    public ResponseEntity<Page<PostCommentDTO>> getAllOfPost(Pageable pageable, @PathVariable("postId") Long postId) {
        return new ResponseEntity<>(postService.getCommentsByPostId(pageable, postId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostCommentDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postService.getComment(id), HttpStatus.OK);
    }

    @PostMapping("/{postId}")
    public ResponseEntity<PostCommentResponse> create(@PathVariable("postId") Long postId, @RequestBody PostCommentRequest request) {
        return new ResponseEntity<>(postService.createComment(postId, request), HttpStatus.CREATED);
    }

    @PostMapping("/reply/{commentId}")
    public ResponseEntity<PostCommentReplyResponse> reply(@PathVariable("commentId") Long commentId, @RequestBody PostCommentRequest request) {
        return new ResponseEntity<>(postService.replyToComment(commentId, request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        postService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
