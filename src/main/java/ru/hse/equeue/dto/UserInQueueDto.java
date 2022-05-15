package ru.hse.equeue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInQueueDto {
    private String id;
    private String name;
    private String photoUrl;
    private String status;
}
