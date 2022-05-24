package ru.hse.equeue.repository;

import com.querydsl.core.types.EntityPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<T, I extends Serializable, Q extends EntityPath<T>> extends JpaRepository<T, I>, QuerydslPredicateExecutor<T>,
        QuerydslBinderCustomizer<Q> {
    @Override
    default void customize(QuerydslBindings bindings, Q entity) {
    }
}