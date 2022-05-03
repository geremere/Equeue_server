package ru.hse.equeue.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QueueDto {
    private String name;
    private Double x;
    private Double y;
    private String photoUrl;
    private UserDto owner;
    private List<UserInQueueDto> usersQueue;
    private QueueStatusDto status;
}
