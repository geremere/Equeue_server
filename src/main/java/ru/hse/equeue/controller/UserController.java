package ru.hse.equeue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.equeue.dto.UserByIdInQueueDto;
import ru.hse.equeue.sevice.UserService;
import ru.hse.equeue.util.EndPoints;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(EndPoints.BASE_USER)
    private UserByIdInQueueDto getById(@RequestParam(name = "userId") String userId, @RequestParam(name = "queueId") Long queueId) {
        return userService.getUserInQueueById(userId, queueId);
    }
}
