package ru.hse.equeue.respository;

import ru.hse.equeue.model.QQueueStatusEnum;
import ru.hse.equeue.model.QUserInQueueStatus;
import ru.hse.equeue.model.QueueStatusEnum;
import ru.hse.equeue.model.UserInQueueStatus;
import ru.hse.equeue.model.enums.EQueueStatus;
import ru.hse.equeue.model.enums.EUserInQueueStatus;

public interface UserInQueueStatusRepository extends BaseRepository<UserInQueueStatus, Long, QUserInQueueStatus>{
    UserInQueueStatus findByName(EUserInQueueStatus status);
}
