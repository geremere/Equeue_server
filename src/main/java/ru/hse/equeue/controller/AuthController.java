package ru.hse.equeue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.equeue.sevice.jwt.AuthService;
import ru.hse.equeue.util.EndPoints;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping(EndPoints.AUTH)
    public String auth(@PathVariable("token") String idToken){
        return authService.auth(idToken);
    }
}
