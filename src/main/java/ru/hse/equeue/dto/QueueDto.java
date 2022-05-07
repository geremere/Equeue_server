package ru.hse.equeue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueueDto {
    private String name;
    private Double x;
    private Double y;
    private String address;
    private String photoUrl;
    private String ownerId;
    private List<UserInQueueDto> usersQueue;
    private QueueStatusDto status;
}
