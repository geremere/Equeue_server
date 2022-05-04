package ru.hse.equeue.sevice;

import com.querydsl.core.types.Predicate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.hse.equeue.exception.BaseException;
import ru.hse.equeue.exception.NotFoundException;
import ru.hse.equeue.exception.message.ExceptionMessage;
import ru.hse.equeue.model.QQueue;
import ru.hse.equeue.model.Queue;
import ru.hse.equeue.model.QueueStatusEnum;
import ru.hse.equeue.model.enums.EQueueStatus;
import ru.hse.equeue.respository.QueueRepository;
import ru.hse.equeue.respository.QueueStatusEnumRepository;
import ru.hse.equeue.sevice.base.AbstractBaseService;

import java.util.Locale;


@Service
@RequiredArgsConstructor
public class QueueService extends AbstractBaseService<Queue, Long, QQueue, QueueRepository> {

    @Getter
    private final QueueRepository repository;

    private final QueueStatusEnumRepository queueStatusEnumRepository;

    public Queue getById(Long id) {
        return get(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.QUEUE_NOT_FOUND));
    }

    public Page<Queue> list(Pageable pageable, Predicate predicate) {
        return findAll(predicate, pageable);
    }

    public Queue create(Queue queue) {
        return save(queue);
    }

    public Queue changeStatus(String status, Long id, String userId) {
        Queue queue = getById(id);
        if (queue.getOwner().getId().equals(userId)) {
            QueueStatusEnum newQueueStatusEnum = queueStatusEnumRepository
                    .findByName(EQueueStatus
                            .valueOf(status
                                    .toUpperCase(Locale.ROOT)));
            queue.getStatus().setStatus(newQueueStatusEnum);
            save(queue);
        }
        throw new BaseException(ExceptionMessage.CHANGE_STATUS_NOT_ALLOWED);
    }

    public Queue update(Long id, Queue queue) {
        Queue oldQueue = getById(id);
        queue.setId(oldQueue.getId());
        queue.setCreatedAt(oldQueue.getCreatedAt());
        return save(queue);
    }
}
