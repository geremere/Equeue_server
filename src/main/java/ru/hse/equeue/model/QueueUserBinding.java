package ru.hse.equeue.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_queue_binding", schema = "public")
@ToString
public class QueueUserBinding {

    @EmbeddedId
    private UserQueueBindingId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User student;

    @ManyToOne
    @MapsId("queueId")
    @JoinColumn(name = "course_id")
    private Queue queue;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private UserInQueueStatus status;

}
