package ru.readzero.util;

import jakarta.validation.constraints.NotNull;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.readzero.entity.user.User;

@UtilityClass
public class UserUtil {

    public static @NotNull User getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) {
            throw new SecurityException("User not authenticated");
        }
        return user;
    }

}
