package ru.hse.equeue.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EQueueStatus {
    ACTIVE("ACTIVE"),
    CLOSED("CLOSED"),
    ON_PAUSE("ON_PAUSE");

    private final String value;

}
