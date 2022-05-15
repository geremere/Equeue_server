package ru.hse.equeue.respository;

import org.springframework.stereotype.Repository;
import ru.hse.equeue.model.QUserInQueueStatus;
import ru.hse.equeue.model.UserInQueueStatus;
import ru.hse.equeue.model.enums.EUserInQueueStatus;

@Repository
public interface UserInQueueStatusRepository extends BaseRepository<UserInQueueStatus, Long, QUserInQueueStatus>{
    UserInQueueStatus findByName(EUserInQueueStatus status);
}
