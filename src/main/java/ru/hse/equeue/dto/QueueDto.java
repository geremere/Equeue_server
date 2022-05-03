package ru.hse.equeue.dto;

import lombok.Builder;
import lombok.Data;
import ru.hse.equeue.model.User;

import java.util.List;

@Data
@Builder
public class QueueDto {
    private String name;
    private Double x;
    private Double y;
    private String photoUrl;
    private User owner;
    private List<UserDto> usersQueue;
}
