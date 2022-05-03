package ru.hse.equeue.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.hse.equeue.dto.CreateQueueDto;
import ru.hse.equeue.dto.QueueDto;
import ru.hse.equeue.model.Queue;
import ru.hse.equeue.model.enums.EQueueStatus;
import ru.hse.equeue.respository.QueueStatusEnumRepository;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class QueueConverter {

    private final ModelMapper modelMapper;
    private final QueueStatusEnumRepository queueStatusEnumRepository;

    public Queue fromDto(QueueDto queueDto) {
        return modelMapper.map(queueDto, Queue.class);
    }

    public Queue fromDto(CreateQueueDto queueDto) {
        Queue queue =  modelMapper.map(queueDto, Queue.class);
        queue.getStatus().setStatus(queueStatusEnumRepository
                .findByName(EQueueStatus
                        .valueOf(queueDto
                                .getStatus()
                                .getStatus()
                                .toUpperCase(Locale.ROOT))));
        return queue;
    }

    public QueueDto toDto(Queue queue) {
        return modelMapper.map(queue, QueueDto.class);
    }
}
