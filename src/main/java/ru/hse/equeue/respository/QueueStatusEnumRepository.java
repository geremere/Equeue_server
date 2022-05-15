package ru.hse.equeue.respository;

import org.springframework.stereotype.Repository;
import ru.hse.equeue.model.QQueueStatusEnum;
import ru.hse.equeue.model.QueueStatusEnum;
import ru.hse.equeue.model.enums.EQueueStatus;

@Repository
public interface QueueStatusEnumRepository extends BaseRepository<QueueStatusEnum, Long, QQueueStatusEnum>{
    QueueStatusEnum findByName(EQueueStatus status);
}
