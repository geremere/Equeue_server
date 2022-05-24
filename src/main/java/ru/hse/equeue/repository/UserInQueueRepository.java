package ru.hse.equeue.repository;

import org.springframework.stereotype.Repository;
import ru.hse.equeue.model.QUserInQueue;
import ru.hse.equeue.model.UserInQueue;

@Repository
public interface UserInQueueRepository extends BaseRepository<UserInQueue, Long, QUserInQueue> {
}
