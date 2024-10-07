package ru.readzero.enums;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ReportDecision {

    APPROVED("Принята"),
    DECLINED("Отклонена");

    String description;
}