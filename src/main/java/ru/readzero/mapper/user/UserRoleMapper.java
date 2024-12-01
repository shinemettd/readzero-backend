package ru.readzero.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.readzero.entity.user.UserRole;
import ru.readzero.payload.user.UserRoleDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserRoleMapper {
    UserRoleDTO toDto(UserRole userRole);
    UserRole toEntity(UserRoleDTO userRoleDTO);
}
