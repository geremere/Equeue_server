package ru.hse.equeue.controller;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.hse.equeue.converter.QueueConverter;
import ru.hse.equeue.dto.CreateQueueDto;
import ru.hse.equeue.dto.PositionDto;
import ru.hse.equeue.dto.QueueDto;
import ru.hse.equeue.sevice.QueueService;
import ru.hse.equeue.util.EndPoints;


@RestController
@RequiredArgsConstructor
public class QueueController {

    private final QueueService queueService;
    private final QueueConverter queueConverter;

    @PostMapping(EndPoints.BASE_QUEUE)
    public QueueDto create(@RequestBody CreateQueueDto queueDto) {
        return queueConverter.toDto(queueService.create(queueConverter.fromDto(queueDto)));
    }

    @GetMapping(EndPoints.QUEUE_BY_ID)
    public QueueDto get(@PathVariable Long id) {
        return queueConverter.toDto(queueService.getById(id));
    }

    @PostMapping(EndPoints.QUEUE_LIST)
    public Page<QueueDto> list(Pageable pageable,
                                     Predicate predicate,
                                     @RequestBody(required = false) PositionDto positionDto) {
        return queueService.list(pageable, predicate).map(queueConverter::toDto);
    }

    @PutMapping(EndPoints.QUEUE_BY_ID)
    public QueueDto update(@PathVariable Long id, @RequestBody CreateQueueDto queueDto) {
        return queueConverter.toDto(queueService.update(id, queueConverter.fromDto(queueDto)));
    }
}
