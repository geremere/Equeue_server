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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_queue_binding",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "queue_id"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<User> usersQueue = new ArrayList<>();

    private Double x;
    private Double y;
    private String photoUrl;

    @OneToOne(fetch = FetchType.LAZY)
    private User owner;
}
