package ru.hse.equeue.sevice;

import com.querydsl.core.types.Predicate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.hse.equeue.exception.NotFoundException;
import ru.hse.equeue.exception.message.ExceptionMessage;
import ru.hse.equeue.model.QQueue;
import ru.hse.equeue.model.Queue;
import ru.hse.equeue.respository.QueueRepository;
import ru.hse.equeue.sevice.base.AbstractBaseService;


@Service
@RequiredArgsConstructor
public class QueueService extends AbstractBaseService<Queue, Long, QQueue, QueueRepository> {

    @Getter
    private final QueueRepository repository;

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

    public Queue update(Long id, Queue queue){
        Queue oldQueue = getById(id);
        oldQueue.setOwner(queue.getOwner());
        oldQueue.setName(queue.getName());
        oldQueue.setUpdatedAt(null);
        return save(oldQueue);
    }
}
