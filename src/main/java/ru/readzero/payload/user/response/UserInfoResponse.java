package ru.readzero.payload.user.response;

import ru.readzero.payload.user.UserDTO;

public record UserInfoResponse(
    UserDTO user,
    String description,
    String avatarUrl
){
}
