package ru.hse.equeue.sevice;

import com.querydsl.core.BooleanBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.equeue.exception.NotFoundException;
import ru.hse.equeue.exception.message.ExceptionMessage;
import ru.hse.equeue.model.QUserInQueue;
import ru.hse.equeue.model.UserInQueue;
import ru.hse.equeue.model.enums.EUserInQueueStatus;
import ru.hse.equeue.respository.UserInQueueRepository;
import ru.hse.equeue.respository.UserInQueueStatusRepository;
import ru.hse.equeue.sevice.base.AbstractBaseService;

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

    public void deleteByUserId(String userId) {
        UserInQueue userInQueue = findByUserId(userId);
        delete(userInQueue);
    }

}
