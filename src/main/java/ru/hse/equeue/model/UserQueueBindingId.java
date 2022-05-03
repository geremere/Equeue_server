package ru.hse.equeue.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserQueueBindingId implements Serializable {
    @Column(name = "user_id")
    private String userId;

    @Column(name = "queue_id")
    private Long queueId;

}
