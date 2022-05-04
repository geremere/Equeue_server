package ru.hse.equeue.model.projection;

public interface UserInQueueProjection {
    String getId();

    String getName();

    String getEmail();

    String getPhotoUrl();

    boolean isFirst();

    int getCurrentPosition();
}
