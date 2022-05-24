package ru.hse.equeue.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.hse.equeue.model.QQueue;
import ru.hse.equeue.model.Queue;

import java.util.Optional;

@Repository
public interface QueueRepository extends BaseRepository<Queue, Long, QQueue> {

    @Query(value = "select queue.* from queue " +
            "join user_queue_binding uqb on queue.id = uqb.queue_id " +
            "    where uqb.user_id = :userId ",
            nativeQuery = true)
    Optional<Queue> getByUserId(@Param(value = "userId") String userId);

    @Query(value = "select queue.* from queue " +
            "order by abs(x-:x), abs(y-:y) ",
            nativeQuery = true)
    Page<Queue> getQueueList(Pageable pageable, @Param(value = "x") Double x, @Param(value = "y") Double y);
}
