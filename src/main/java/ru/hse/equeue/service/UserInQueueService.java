package ru.hse.equeue.service;

import com.querydsl.core.BooleanBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.equeue.exception.NotFoundException;
import ru.hse.equeue.exception.message.ExceptionMessage;
import ru.hse.equeue.model.QUserInQueue;
import ru.hse.equeue.model.UserInQueue;
import ru.hse.equeue.model.enums.EUserInQueueStatus;
import ru.hse.equeue.repository.UserInQueueRepository;
import ru.hse.equeue.repository.UserInQueueStatusRepository;
import ru.hse.equeue.service.base.AbstractBaseService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInQueueService extends AbstractBaseService<UserInQueue, Long, QUserInQueue, UserInQueueRepository> {

    @Getter
    private final UserInQueueRepository repository;
    private final UserInQueueStatusRepository userInQueueStatusRepository;

    public UserInQueue create(UserInQueue userInQueue) {
        userInQueue.setStatus(userInQueueStatusRepository.findByName(EUserInQueueStatus.IN_QUEUE));
        return save(userInQueue);
    }

    public UserInQueue findByUserId(String userId) {
        BooleanBuilder booleanBuilder = new BooleanBuilder(QUserInQueue.userInQueue.user.id.eq(userId));
        return get(booleanBuilder)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.USER_NOT_FOUND));
    }

    public boolean isExistByUserId(String userId) {
        BooleanBuilder booleanBuilder = new BooleanBuilder(QUserInQueue.userInQueue.user.id.eq(userId));
        Optional<UserInQueue> user = get(booleanBuilder);
        if (user.isPresent()) {
            return true;
        } else {
            return false;
        }

    }

    public void deleteByUserId(String userId) {
        UserInQueue userInQueue = findByUserId(userId);
        delete(userInQueue);
    }

    public Optional<UserInQueue> deleteServised(Long queueId) {
        BooleanBuilder booleanBuilder = new BooleanBuilder(QUserInQueue.userInQueue.queue.id.eq(queueId));
        booleanBuilder.and(QUserInQueue.userInQueue.status.eq(userInQueueStatusRepository.findByName(EUserInQueueStatus.SERVISED)));
        Optional<UserInQueue> userInQueue = get(booleanBuilder);
        userInQueue.ifPresent(this::delete);
        return userInQueue;
    }

    public void newServe(String userId, Long queueId) {
        BooleanBuilder booleanBuilder = new BooleanBuilder(QUserInQueue.userInQueue.queue.id.eq(queueId));
        booleanBuilder.and(QUserInQueue.userInQueue.user.id.eq(userId));
        booleanBuilder.and(QUserInQueue.userInQueue.status.eq(userInQueueStatusRepository.findByName(EUserInQueueStatus.IN_QUEUE)));
        UserInQueue userInQueue = get(booleanBuilder)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.USER_NOT_FOUND));
        userInQueue.setStatus(userInQueueStatusRepository.findByName(EUserInQueueStatus.SERVISED));
        save(userInQueue);
    }

}
