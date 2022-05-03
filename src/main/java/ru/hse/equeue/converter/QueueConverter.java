package ru.hse.equeue.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.hse.equeue.dto.CreateQueueDto;
import ru.hse.equeue.dto.QueueDto;
import ru.hse.equeue.model.Queue;

@Component
@RequiredArgsConstructor
public class QueueConverter {

    private final ModelMapper modelMapper;

    public Queue fromDto(QueueDto queueDto) {
        return modelMapper.map(queueDto, Queue.class);
    }

    public Queue fromDto(CreateQueueDto queueDto) {
        return modelMapper.map(queueDto, Queue.class);
    }

    public CreateQueueDto toCreateDto(Queue queue) {
        return modelMapper.map(queue, CreateQueueDto.class);
    }

    public QueueDto toDto(Queue queue) {
        return modelMapper.map(queue, QueueDto.class);
    }
}
