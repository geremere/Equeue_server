package ru.hse.equeue.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QueueStatusDto {
    private int currentUsersCount;
    private int totalUsersCount;
    private double serviceTime;
    private String status;
}
