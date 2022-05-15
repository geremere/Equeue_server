package ru.hse.equeue.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.hse.equeue.model.base.BaseNamedDeletedEntity;

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
    List<UserInQueue> usersQueue = new ArrayList<>();

    private Double x;
    private Double y;
    private String photoUrl;
    private String address;

    @OneToOne(fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.JOIN)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties
    private User owner;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @Fetch(value = FetchMode.JOIN)
    @JoinColumn(name = "status_id")
    private QueueStatus status;
}
