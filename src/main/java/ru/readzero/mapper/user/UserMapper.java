package ru.readzero.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.readzero.entity.user.User;
import ru.readzero.payload.user.UserDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserDTO userToUserDto(User user);
    User userDtoToUser(UserDTO userDTO);
}
