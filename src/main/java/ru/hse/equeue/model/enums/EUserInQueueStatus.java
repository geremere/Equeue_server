package ru.hse.equeue.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EUserInQueueStatus {
    SERVISED("SERVISED"),
    IN_QUEUE("IN_QUEUE");

    private final String value;

}
