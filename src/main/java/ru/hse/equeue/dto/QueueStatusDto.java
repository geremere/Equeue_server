package ru.hse.equeue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueueStatusDto {
    private int currentUsersCount;
    private int totalUsersCount;
    private double serviceTime;
    private String status;
}
