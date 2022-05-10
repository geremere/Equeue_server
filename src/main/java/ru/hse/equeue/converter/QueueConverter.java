package ru.hse.equeue.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.hse.equeue.dto.CreateQueueDto;
import ru.hse.equeue.dto.QueueDto;
import ru.hse.equeue.model.Queue;
import ru.hse.equeue.respository.QueueStatusEnumRepository;

@Component
@RequiredArgsConstructor
public class QueueConverter {

    private final ModelMapper queueMapper;
    private final QueueStatusEnumRepository queueStatusEnumRepository;

    public Queue fromDto(QueueDto queueDto) {
        return queueMapper.map(queueDto, Queue.class);
    }

    public Queue fromDto(CreateQueueDto queueDto) {
        return queueMapper.map(queueDto, Queue.class);
    }

    public QueueDto toDto(Queue queue) {
        return queueMapper.map(queue, QueueDto.class);
    }
}
