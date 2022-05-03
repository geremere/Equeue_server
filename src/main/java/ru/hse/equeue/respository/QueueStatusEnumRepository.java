package ru.hse.equeue.respository;

import ru.hse.equeue.model.QQueueStatusEnum;
import ru.hse.equeue.model.QueueStatusEnum;
import ru.hse.equeue.model.enums.EQueueStatus;

public interface QueueStatusEnumRepository extends BaseRepository<QueueStatusEnum, Long, QQueueStatusEnum>{
    QueueStatusEnum findByName(EQueueStatus status);
}
