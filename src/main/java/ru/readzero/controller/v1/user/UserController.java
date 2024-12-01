package ru.readzero.controller.v1.user;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.readzero.aop.HandleUserMetadata;
import ru.readzero.enums.session.SessionActionType;
import ru.readzero.payload.user.request.PasswordChangeRequest;
import ru.readzero.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    public ResponseEntity<?> getAllNonBlocked(Pageable pageable) {
        return null;
    }

    @PostMapping("/change-password")
    @HandleUserMetadata(actionType = SessionActionType.CHANGE_PASSWORD)
    public ResponseEntity<Void> changePassword(@RequestBody PasswordChangeRequest passwordChangeRequest) {
        userService.changePassword(passwordChangeRequest);
        return ResponseEntity.noContent().build();
    }

}
