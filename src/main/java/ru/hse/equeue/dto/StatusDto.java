package ru.hse.equeue.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatusDto {
    int countOfUsers;
    double averageServiceTime;
}
