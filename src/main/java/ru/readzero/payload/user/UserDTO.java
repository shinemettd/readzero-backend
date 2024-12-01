package ru.readzero.payload.user;

public record UserDTO(
        Long id,
        String username,
        String email,
        boolean isBlocked,
        UserRoleDTO role
) {
}
