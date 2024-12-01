package ru.readzero.controller.v1.post;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.readzero.payload.post.request.PostRequest;
import ru.readzero.payload.post.response.PostResponse;
import ru.readzero.service.PostService;

@RestController
@RequestMapping("/v1/posts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {

    PostService postService;

    @GetMapping
    public ResponseEntity<Page<PostResponse>> getPosts(Pageable pageable) {
        return new ResponseEntity<>(postService.getPosts(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postService.getPost(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest) {
        return new ResponseEntity<>(postService.create(postRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> editPost(@PathVariable("id") Long id, @RequestBody PostRequest postRequest) {
        return new ResponseEntity<>(postService.update(id, postRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PostResponse> deletePost(@PathVariable("id") Long id) {
        return new ResponseEntity<>(postService.deleteById(id), HttpStatus.NO_CONTENT);
    }

}
