package ru.readzero.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.readzero.entity.user.User;
import ru.readzero.exception.NotFoundException;
import ru.readzero.exception.user.PasswordException;
import ru.readzero.mapper.user.UserInfoMapper;
import ru.readzero.mapper.user.UserMapper;
import ru.readzero.payload.user.request.AvatarUpdateRequest;
import ru.readzero.payload.user.request.PasswordChangeRequest;
import ru.readzero.payload.user.request.UserInfoRequest;
import ru.readzero.payload.user.response.UserInfoResponse;
import ru.readzero.repository.UserRepository;
import ru.readzero.service.UserService;
import ru.readzero.util.UserUtil;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    UserMapper userMapper;
    UserInfoMapper userInfoMapper;

    @Override
    public void changePassword(PasswordChangeRequest request) {
        User user = UserUtil.getCurrentUser();

        if (!request.newPassword().equals(request.confirmPassword())) {
            throw new PasswordException("Incorrect confirm password!");
        }

        boolean isRightPassword = passwordEncoder.matches(user.getPassword(), request.oldPassword());
        if (!isRightPassword) {
            throw new PasswordException("Incorrect password!");
        }

        user.setPassword(passwordEncoder.encode(request.newPassword()));

    }

    @Override
    public UserInfoResponse getUserInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + userId));
        return userInfoMapper.toResponse(user.getUserInfo());
    }

    @Override
    public UserInfoResponse getCurrentUserInfo() {
        return userInfoMapper.toResponse(UserUtil.getCurrentUser().getUserInfo());
    }

    @Override
    public UserInfoResponse changeUserInfo(UserInfoRequest req) {
        User user = UserUtil.getCurrentUser();
        user.getUserInfo().setDescription(req.description());
        user = userRepository.save(user);
        return userInfoMapper.toResponse(user.getUserInfo());
    }

    @Override
    public void changeAvatar(AvatarUpdateRequest req) {
        User user = UserUtil.getCurrentUser();
        user.getUserInfo().setAvatarUrl(req.avatarUrl());
        userRepository.save(user);
    }
}
