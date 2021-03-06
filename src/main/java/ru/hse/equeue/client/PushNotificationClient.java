package ru.hse.equeue.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.hse.equeue.dto.NotificationRequest;

@Component
@Slf4j
public class PushNotificationClient {

    @Value("${firebase.notifications.token}")
    private String notificationToken;

    private WebClient notificationWebClient = WebClient.builder()
            .baseUrl("https://fcm.googleapis.com/fcm/send")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    public void pushNotification(NotificationRequest notificationRequest) {
        notificationWebClient.post()
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "key=" + notificationToken)
                .bodyValue(notificationRequest)
                .retrieve()
                .bodyToMono(Object.class)
                .onErrorResume(error -> {
                    log.error(error.getMessage());
                    return Mono.error(error);
                })
                .block();
    }
}
