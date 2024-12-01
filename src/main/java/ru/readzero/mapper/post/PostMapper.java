package ru.readzero.mapper.post;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.readzero.entity.post.Post;
import ru.readzero.payload.post.request.PostRequest;
import ru.readzero.payload.post.response.PostResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostMapper {
    Post toEntity(PostRequest postRequest);
    @Mapping(target = "authorId", source = "author.id")
    PostResponse toResponse(Post post);
}
