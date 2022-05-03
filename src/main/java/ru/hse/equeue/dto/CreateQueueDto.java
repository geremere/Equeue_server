package ru.hse.equeue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hse.equeue.model.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateQueueDto {
    private double x;
    private double y;
    private String name;
    private User owner;
    private String photoUrl;
    private QueueStatusDto status;
}
