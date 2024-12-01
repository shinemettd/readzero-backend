package ru.readzero.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.readzero.entity.user.UserInfo;
import ru.readzero.payload.user.response.UserInfoResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserInfoMapper {
    UserInfoResponse toResponse(UserInfo userInfo);
    UserInfo toEntity(UserInfoResponse userInfoResponse);
}
