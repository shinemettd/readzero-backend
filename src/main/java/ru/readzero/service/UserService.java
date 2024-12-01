package ru.readzero.service;

import ru.readzero.payload.user.request.AvatarUpdateRequest;
import ru.readzero.payload.user.request.PasswordChangeRequest;
import ru.readzero.payload.user.request.UserInfoRequest;
import ru.readzero.payload.user.response.UserInfoResponse;

public interface UserService {

    void changePassword(PasswordChangeRequest passwordChangeRequest);
    UserInfoResponse getUserInfo(Long userId);
    UserInfoResponse getCurrentUserInfo();
    UserInfoResponse changeUserInfo(UserInfoRequest userInfoRequest);
    void changeAvatar(AvatarUpdateRequest avatarUpdateRequest);
}
