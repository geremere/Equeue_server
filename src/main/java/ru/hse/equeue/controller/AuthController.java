package ru.hse.equeue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.equeue.service.jwt.AuthService;
import ru.hse.equeue.util.EndPoints;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping(EndPoints.AUTH)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String auth(@PathVariable("token") String idToken){
        return authService.auth(idToken);
    }
}
