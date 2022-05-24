package ru.hse.equeue.service;

import com.querydsl.core.BooleanBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.hse.equeue.client.AmazonAwsS3Client;
import ru.hse.equeue.client.PushNotificationClient;
import ru.hse.equeue.dto.Notification;
import ru.hse.equeue.dto.NotificationRequest;
import ru.hse.equeue.dto.PositionDto;
import ru.hse.equeue.exception.BaseException;
import ru.hse.equeue.exception.NotFoundException;
import ru.hse.equeue.exception.message.ExceptionMessage;
import ru.hse.equeue.model.*;
import ru.hse.equeue.model.Queue;
import ru.hse.equeue.model.base.BaseEntity;
import ru.hse.equeue.model.enums.EQueueStatus;
import ru.hse.equeue.repository.QueueRepository;
import ru.hse.equeue.repository.QueueStatusEnumRepository;
import ru.hse.equeue.service.base.AbstractBaseService;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class QueueService extends AbstractBaseService<Queue, Long, QQueue, QueueRepository> {

    @Getter
    private final QueueRepository repository;

    private final UserService userService;

    private final QueueStatusEnumRepository queueStatusEnumRepository;
    private final UserInQueueService userInQueueService;

    private final AmazonAwsS3Client s3Client;
    private final PushNotificationClient notificationClient;


    public Queue getById(Long id) {
        return get(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.QUEUE_NOT_FOUND));
    }

    public Page<Queue> list(Pageable pageable, PositionDto positionDto, Optional<String> search) {
        if (search.isPresent()) {
            return repository.searchQueueList(pageable, search.get(), positionDto.getX(), positionDto.getY());
        } else {
            return repository.getQueueList(pageable, positionDto.getX(), positionDto.getY());
        }
    }

    public List<Queue> list(PositionDto positionDto) {
        BooleanBuilder booleanBuilder = new BooleanBuilder(QQueue.queue.x.between(positionDto.getX() - positionDto.getR(), positionDto.getX() + positionDto.getR()));
        booleanBuilder.and(QQueue.queue.y.between(positionDto.getY() - positionDto.getR(), positionDto.getY() + positionDto.getR()));
        return findAll(booleanBuilder);
    }

    public Queue create(Queue queue, MultipartFile image) {
        queue.getStatus().setStatus(queueStatusEnumRepository
                .findByName(EQueueStatus.CLOSED));
        String imageName = s3Client.uploadFile(image);
        queue.setPhotoUrl(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/" + imageName);

        return save(queue);
    }

    public Queue changeStatus(String status, String userId) {
        User user = userService.getById(userId);
        if (user.getQueue() == null) {
            throw new NotFoundException(ExceptionMessage.CHANGE_STATUS_NOT_ALLOWED);
        }
        Queue queue = getById(user.getQueue().getId());
        QueueStatusEnum newQueueStatusEnum = queueStatusEnumRepository
                .findByName(EQueueStatus
                        .valueOf(status
                                .toUpperCase(Locale.ROOT)));
        queue.getStatus().setStatus(newQueueStatusEnum);
        return save(queue);
    }

    public Queue getByUserId(String userId) {
        return repository.getByUserId(userId)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.QUEUE_NOT_FOUND));
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

    public Queue standToQueue(String userId, Long queueId) {
        Queue queue = getById(queueId);
        User user = userService.getById(userId);
        if (user.getId().equals(queue.getOwner().getId())) {
            throw new BaseException(ExceptionMessage.STAND_TO_QUEUE_OWNER);
        }
        if (userInQueueService.isExistByUserId(user.getId())) {
            throw new BaseException(ExceptionMessage.STAND_TO_SECOND_QUEUE);
        }
        userInQueueService.create(UserInQueue.builder()
                .queue(queue)
                .user(user)
                .build());
        queue = getById(queueId);
        queue.getStatus().setCurrentUsersCount(queue.getStatus().getCurrentUsersCount() + 1);
        return save(queue);
    }

    public void removeUserFromQueue(String userId, Long queueId) {
        Queue queue = getById(queueId);
        userInQueueService.deleteByUserId(userId);
        queue = getById(queueId);
        queue.getStatus().setCurrentUsersCount(queue.getStatus().getCurrentUsersCount() - 1);
        save(queue);
    }

    public Queue serve(String ownerId, Optional<String> userId) {
        User user = userService.getById(ownerId);
        Optional<UserInQueue> servisedUserInQueue = userInQueueService.deleteServised(user.getQueue().getId());
        userId.ifPresent(id -> userInQueueService.newServe(id, user.getQueue().getId()));
        Queue queue = getById(user.getQueue().getId());
        queue.getUsersQueue().sort(Comparator.comparing(BaseEntity::getCreatedAt));
        queue.getUsersQueue().stream().limit(3).skip(1).forEach(userInQueue -> {
            NotificationRequest request = NotificationRequest.builder()
                    .to(userInQueue.getUser().getFirebaseToken())
                    .notification(Notification.builder()
                            .title("Скоро ваша очередь")
                            .body("Ваша очередь примерно через " +
                                    queue.getUsersQueue().indexOf(userInQueue) * queue.getStatus().getServiceTime() + "минут")
                            .build())
                    .build();
            notificationClient.pushNotification(request);
        });


        servisedUserInQueue.ifPresent(userInQueue -> {
            long delta = (new Date().getTime() - userInQueue.getUpdatedAt().getTime()) / 1000;
            queue.getStatus()
                    .setServiceTime(
                            (queue.getStatus().getServiceTime() * queue.getStatus().getTotalUsersCount() + delta) /
                                    (queue.getStatus().getTotalUsersCount() + 1));
            queue.getStatus().setTotalUsersCount(queue.getStatus().getTotalUsersCount() + 1);
            queue.getStatus().setCurrentUsersCount(queue.getStatus().getCurrentUsersCount() - 1);
        });
        return save(queue);
    }
}
