package ru.hse.equeue.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
    @Fetch(value = FetchMode.SUBSELECT)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<QueueUserBinding> usersQueue = new ArrayList<>();

    private Double x;
    private Double y;
    private String photoUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.JOIN)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @Fetch(value = FetchMode.JOIN)
    @JoinColumn(name = "status_id")
    private QueueStatus status;
}
