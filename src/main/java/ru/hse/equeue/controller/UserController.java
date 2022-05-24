package ru.hse.equeue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hse.equeue.config.CurrentUser;
import ru.hse.equeue.converter.UserConverter;
import ru.hse.equeue.dto.UserByIdInQueueDto;
import ru.hse.equeue.dto.UserDto;
import ru.hse.equeue.model.CustomUserDetails;
import ru.hse.equeue.service.UserService;
import ru.hse.equeue.util.EndPoints;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserConverter userConverter;

    @GetMapping(EndPoints.BASE_USER)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    private UserByIdInQueueDto getById(@RequestParam(name = "userId") String userId, @RequestParam(name = "queueId") Long queueId) {
        return userService.getUserInQueueById(userId, queueId);
    }

    @GetMapping(EndPoints.BASE_USER + EndPoints.GET)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    private UserDto getUser(@CurrentUser CustomUserDetails user) {
        return userConverter.toDto(userService.getById(user.getUserId()));
    }

    @PutMapping(EndPoints.PUT_FIREBASE_TOKEN)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    private void addToken(@CurrentUser CustomUserDetails user,
                          @RequestBody String token) {
        userService.putFirebaseToken(user.getUserId(), token);
    }
}
