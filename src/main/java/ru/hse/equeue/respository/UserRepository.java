package ru.hse.equeue.respository;

import org.springframework.stereotype.Repository;
import ru.hse.equeue.model.QUser;
import ru.hse.equeue.model.User;

@Repository
public interface UserRepository extends BaseRepository<User, String, QUser> {
}
