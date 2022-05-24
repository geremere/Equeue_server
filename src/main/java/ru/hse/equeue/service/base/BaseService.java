package ru.hse.equeue.service.base;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.hse.equeue.model.base.IEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<T extends IEntity<I>, I extends Serializable> {

    Optional<T> get(I id);

    Page<T> findAll(Predicate predicate, Pageable pageable);

    Page<T> findAll(Pageable pageable);

    List<T> findAll(Predicate predicate);

    List<T> findAll();

    T save(T t);

    void delete(T t);

    void delete(I id);

    boolean isExist(I id);

    Optional<T> get(Predicate predicate);
}
