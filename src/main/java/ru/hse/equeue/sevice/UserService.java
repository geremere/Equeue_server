package ru.hse.equeue.sevice;

import com.google.api.client.util.Lists;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.hse.equeue.dto.UserByIdInQueueDto;
import ru.hse.equeue.exception.NotFoundException;
import ru.hse.equeue.exception.message.ExceptionMessage;
import ru.hse.equeue.model.QUserInQueue;
import ru.hse.equeue.model.QUser;
import ru.hse.equeue.model.UserInQueue;
import ru.hse.equeue.model.User;
import ru.hse.equeue.model.base.BaseEntity;
import ru.hse.equeue.model.enums.EUserInQueueStatus;
import ru.hse.equeue.respository.UserInQueueRepository;
import ru.hse.equeue.respository.UserRepository;
import ru.hse.equeue.sevice.base.AbstractBaseService;

import java.util.Comparator;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService extends AbstractBaseService<User, String, QUser, UserRepository> {

    @Getter
    private final UserRepository repository;

    private final UserInQueueRepository queueUserBindingRepository;

    private final UserInQueueRepository userQueueBindingRepository;

    public User getUserByName(String name) {
        return super.get(QUser.user.name.eq(name))
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.USER_NOT_FOUND));
    }

    public User getById(String userId) {
        return get(userId)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.USER_NOT_FOUND));
    }

    public Page<User> list(Pageable pageable, Predicate predicate) {
        return findAll(predicate, pageable);
    }

    public User create(User user) {
        return save(user);
    }

    public UserByIdInQueueDto getUserInQueueById(String userId, Long queueId) {
        BooleanBuilder booleanBuilder = new BooleanBuilder(QUserInQueue.userInQueue.queue.id.eq(queueId));
        booleanBuilder.and(QUserInQueue.userInQueue.status.name.eq(EUserInQueueStatus.IN_QUEUE));
        List<UserInQueue> usersInQueue = Lists.newArrayList(queueUserBindingRepository.findAll(booleanBuilder));
        usersInQueue.sort(Comparator.comparing(BaseEntity::getCreatedAt));
        UserInQueue resUser = usersInQueue.stream().filter(it -> it.getUser().getId().equals(userId)).findFirst()
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.USER_NOT_FOUND));

        return UserByIdInQueueDto.builder()
                .id(resUser.getUser().getId())
                .name(resUser.getUser().getName())
                .currentPosition(usersInQueue.indexOf(resUser) + 1)
                .build();
    }
}
