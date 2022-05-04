package ru.hse.equeue.respository;

import org.springframework.stereotype.Repository;
import ru.hse.equeue.model.QQueueUserBinding;
import ru.hse.equeue.model.QueueUserBinding;

@Repository
public interface QueueUserBindingRepository extends BaseRepository<QueueUserBinding, Long, QQueueUserBinding> {
}
