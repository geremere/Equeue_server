package ru.hse.equeue.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.hse.equeue.model.base.IEntity;

import javax.persistence.*;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "queue_status", schema = "public")
@ToString
public class QueueStatus implements IEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "count_of_users")
    private int currentUsersCount;

    @Column(name = "total_count_of_users")
    private int totalUsersCount;

    @Column(name = "average_service_time")
    private double serviceTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private QueueStatusEnum status;

}
