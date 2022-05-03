package ru.hse.equeue.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PositionDto {
    private Double x;
    private Double y;
    private Double r;
}
