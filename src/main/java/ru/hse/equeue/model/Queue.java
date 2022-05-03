package ru.hse.equeue.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.hse.equeue.model.base.BaseNamedDeletedEntity;
import ru.hse.equeue.model.base.BaseNamedEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "queue", schema = "public")
@ToString
@EqualsAndHashCode(callSuper = true)
public class Queue extends BaseNamedDeletedEntity {

    @OneToMany(mappedBy = "queue", fetch = FetchType.EAGER)
    List<QueueUserBinding> usersQueue = new ArrayList<>();

    private Double x;
    private Double y;
    private String photoUrl;

    @OneToOne(fetch = FetchType.LAZY)
    private User owner;

    @OneToOne(fetch = FetchType.LAZY)
    private QueueStatus status;
}
