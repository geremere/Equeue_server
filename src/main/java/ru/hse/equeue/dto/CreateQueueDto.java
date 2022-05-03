package ru.hse.equeue.dto;

import lombok.Builder;
import lombok.Data;
import ru.hse.equeue.model.User;

@Data
@Builder
public class CreateQueueDto {
    private String name;
    private User owner;
    private String photoUrl;
}
