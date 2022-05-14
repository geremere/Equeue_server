package ru.hse.equeue.sevice;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.hse.equeue.client.AmazonAwsS3Client;
import ru.hse.equeue.dto.PositionDto;
import ru.hse.equeue.exception.BaseException;
import ru.hse.equeue.exception.NotFoundException;
import ru.hse.equeue.exception.message.ExceptionMessage;
import ru.hse.equeue.model.QQueue;
import ru.hse.equeue.model.QQueueUserBinding;
import ru.hse.equeue.model.Queue;
import ru.hse.equeue.model.QueueStatusEnum;
import ru.hse.equeue.model.base.BaseEntity;
import ru.hse.equeue.model.enums.EQueueStatus;
import ru.hse.equeue.respository.QueueRepository;
import ru.hse.equeue.respository.QueueStatusEnumRepository;
import ru.hse.equeue.sevice.base.AbstractBaseService;

import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class QueueService extends AbstractBaseService<Queue, Long, QQueue, QueueRepository> {

    @Getter
    private final QueueRepository repository;

    private final QueueStatusEnumRepository queueStatusEnumRepository;

    private final AmazonAwsS3Client s3Client;


    public Queue getById(Long id) {
        return get(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.QUEUE_NOT_FOUND));
    }

    public Page<Queue> list(Pageable pageable, PositionDto positionDto) {
        BooleanBuilder booleanBuilder = new BooleanBuilder(QQueue.queue.x.between(positionDto.getX() - positionDto.getR(), positionDto.getX() + positionDto.getR()));
        booleanBuilder.and(QQueue.queue.y.between(positionDto.getY() - positionDto.getR(), positionDto.getY() + positionDto.getR()));
        return findAll(booleanBuilder, pageable);
    }

    public Queue create(Queue queue, MultipartFile image) {
        queue.getStatus().setStatus(queueStatusEnumRepository
                .findByName(EQueueStatus.CLOSED));
        String imageName = s3Client.uploadFile(image);
        queue.setPhotoUrl(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/" + imageName);

        return save(queue);
    }

    public Queue changeStatus(String status, Long id, String userId) {
        Queue queue = getById(id);
        if (queue.getOwner().getId().equals(userId)) {
            QueueStatusEnum newQueueStatusEnum = queueStatusEnumRepository
                    .findByName(EQueueStatus
                            .valueOf(status
                                    .toUpperCase(Locale.ROOT)));
            queue.getStatus().setStatus(newQueueStatusEnum);
            save(queue);
        }
        throw new BaseException(ExceptionMessage.CHANGE_STATUS_NOT_ALLOWED);
    }

    public Queue getByUserId(String userId) {
        return repository.getByUserId(userId)
                .orElseThrow(() -> new BaseException(ExceptionMessage.QUEUE_NOT_FOUND));
    }

    public Queue getByOwnerId(String ownerId) {
        BooleanBuilder booleanBuilder = new BooleanBuilder(QQueue.queue.owner.id.eq(ownerId));
        Queue queue = get(booleanBuilder)
                .orElseThrow(() -> new BaseException(ExceptionMessage.QUEUE_NOT_FOUND));
        queue.setUsersQueue(queue.getUsersQueue().stream()
                .sorted(Comparator.comparing(BaseEntity::getCreatedAt))
                .collect(Collectors.toList()));
        return queue;
    }

    public Queue update(Long id, Queue queue) {
        Queue oldQueue = getById(id);
        queue.setId(oldQueue.getId());
        queue.setCreatedAt(oldQueue.getCreatedAt());
        return save(queue);
    }
}
