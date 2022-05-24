package ru.hse.equeue.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.hse.equeue.model.QUser;
import ru.hse.equeue.model.UserInQueue;
import ru.hse.equeue.model.User;

@Repository
public interface UserRepository extends BaseRepository<User, String, QUser> {

    @Query(
            value = "select uqb " +
                    "from user_queue_binding uqb " +
                    "join user_in_queue_status uiqs on uqb.status_id = uiqs.id " +
                    "where uqb.queue_id >= :queueId " +
                    "and uqb.queue_id <= :queueId " +
                    "and uiqs.name like \'IN_QUEUE\' " +
                    "order by created_at ",
            nativeQuery = true
    )
    UserInQueue getUserInQueueByProjection(@Param("queueId")Long queueId);
}
