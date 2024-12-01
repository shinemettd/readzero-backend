package ru.readzero.enums.session;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public enum DeviceType {
    DESKTOP,
    MOBILE,
    TABLET,
    BOT,
    OTHER
}
