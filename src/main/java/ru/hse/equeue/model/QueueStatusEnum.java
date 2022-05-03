package ru.hse.equeue.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.hse.equeue.model.base.IEntity;
import ru.hse.equeue.model.enums.EQueueStatus;
import ru.hse.equeue.model.enums.ERole;

import javax.persistence.*;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "queue_status_enum", schema = "public")
public class QueueStatusEnum implements IEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EQueueStatus name;

    @Override
    public String toString(){
        return name.getValue();
    }
}
