package ru.hse.equeue.repository;

import org.springframework.stereotype.Repository;
import ru.hse.equeue.model.QRole;
import ru.hse.equeue.model.Role;
import ru.hse.equeue.model.enums.ERole;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long, QRole> {
    Role findByName(ERole enumRole);
}
