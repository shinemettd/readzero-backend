package ru.readzero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.readzero.entity.post.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
