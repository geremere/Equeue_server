package ru.hse.equeue.model.base;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@QueryEntity
public class BaseNamedDeletedEntity extends BaseDeletedEntity implements INamedEntity {
    @Column(name = "name")
    private String name;
}
