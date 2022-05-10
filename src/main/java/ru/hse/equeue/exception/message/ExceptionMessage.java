package ru.hse.equeue.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionMessage
{
    INTERNAL_SERVER_ERROR("Something went wrong"),
    NOT_FOUND("Not found"),
    UNAUTHORIZED("You not authorized"),
    USER_NOT_FOUND("User not found"),
    QUEUE_NOT_FOUND("Queue not found"),
    IMAGE_NOT_UPLOADED("Image not uploaded"),
    CHANGE_STATUS_NOT_ALLOWED("You haven't allow to change status of queue");

    private final String value;
}
