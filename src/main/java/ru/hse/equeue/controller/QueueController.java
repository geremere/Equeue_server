package ru.hse.equeue.controller;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hse.equeue.config.CurrentUser;
import ru.hse.equeue.converter.QueueConverter;
import ru.hse.equeue.dto.CreateQueueDto;
import ru.hse.equeue.dto.PositionDto;
import ru.hse.equeue.dto.QueueDto;
import ru.hse.equeue.model.CustomUserDetails;
import ru.hse.equeue.sevice.QueueService;
import ru.hse.equeue.util.EndPoints;


@RestController
@RequiredArgsConstructor
public class QueueController {

    private final QueueService queueService;
    private final QueueConverter queueConverter;

    @PostMapping(EndPoints.BASE_QUEUE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public QueueDto create(@RequestPart(value = "queue") CreateQueueDto queueDto,
                           @RequestPart(value = "image") MultipartFile image) {
        return queueConverter.toDto(queueService.create(queueConverter.fromDto(queueDto), image));
    }

    @PutMapping(EndPoints.BASE_QUEUE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public QueueDto changeStatus(@CurrentUser CustomUserDetails user,
                                 @RequestParam(name = "status") String status
            , @RequestParam(name = "id") Long id) {
        return queueConverter.toDto(queueService.changeStatus(status, id, user.getUserId()));
    }

    @GetMapping(EndPoints.QUEUE_BY_USER_ID)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public QueueDto getByUserId(@CurrentUser CustomUserDetails user) {
        return queueConverter.toDto(queueService.getByUserId(user.getUserId()));
    }

    @GetMapping(EndPoints.QUEUE_BY_OWNER_ID)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public QueueDto getByOwnerId(@CurrentUser CustomUserDetails user) {
        return queueConverter.toDto(queueService.getByOwnerId(user.getUserId()));
    }

    @GetMapping(EndPoints.QUEUE_BY_ID)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public QueueDto get(@PathVariable Long id) {
        return queueConverter.toDto(queueService.getById(id));
    }

    @PostMapping(EndPoints.QUEUE_LIST)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Page<QueueDto> list(Pageable pageable,
                               @RequestBody(required = false) PositionDto positionDto) {
        return queueService.list(pageable, positionDto).map(queueConverter::toDto);
    }

    @PutMapping(EndPoints.QUEUE_BY_ID)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public QueueDto update(@PathVariable Long id, @RequestBody QueueDto queueDto) {
        return queueConverter.toDto(queueService.update(id, queueConverter.fromDto(queueDto)));
    }
}
