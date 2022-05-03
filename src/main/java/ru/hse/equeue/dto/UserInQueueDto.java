package ru.hse.equeue.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInQueueDto {
    private UserDto user;
    private String status;
}
