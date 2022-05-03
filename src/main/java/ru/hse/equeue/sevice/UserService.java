package ru.hse.equeue.sevice;

import com.querydsl.core.types.Predicate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.hse.equeue.exception.NotFoundException;
import ru.hse.equeue.exception.message.ExceptionMessage;
import ru.hse.equeue.model.QUser;
import ru.hse.equeue.model.User;
import ru.hse.equeue.respository.UserRepository;
import ru.hse.equeue.sevice.base.AbstractBaseService;


@Service
@RequiredArgsConstructor
public class UserService extends AbstractBaseService<User, String, QUser, UserRepository> {

    @Getter
    private final UserRepository repository;

    public User getUserByName(String name){
        return super.get(QUser.user.name.eq(name))
                .orElseThrow(()-> new NotFoundException(ExceptionMessage.USER_NOT_FOUND));
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
}
