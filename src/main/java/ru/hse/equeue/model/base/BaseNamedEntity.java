package ru.hse.equeue.model.base;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@Data
@QueryEntity
public class BaseNamedEntity extends BaseEntity implements INamedEntity{
    @Column(name = "name")
    private String name;
}
