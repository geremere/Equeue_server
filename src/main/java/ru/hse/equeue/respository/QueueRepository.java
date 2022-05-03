package ru.hse.equeue.respository;

import org.springframework.stereotype.Repository;
import ru.hse.equeue.model.QQueue;
import ru.hse.equeue.model.Queue;

@Repository
public interface QueueRepository extends BaseRepository<Queue, Long, QQueue>{
}
