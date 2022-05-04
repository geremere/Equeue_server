package ru.hse.equeue.sevice.jwt;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.equeue.model.User;
import ru.hse.equeue.model.enums.ERole;
import ru.hse.equeue.respository.RoleRepository;
import ru.hse.equeue.sevice.UserService;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final JwtProvider jwtProvider;

    public String auth(String idTokenString) {
        GoogleIdToken idToken = jwtProvider.validateAndGetGoogleIdToken(idTokenString);
        GoogleIdToken.Payload payload = idToken.getPayload();
        if (!userService.isExist(payload.getSubject())) {
            userService.create(User.builder()
                    .id(payload.getSubject())
                    .name((String) payload.get("name"))
                    .email(payload.getEmail())
                    .photoURL((String) payload.get("picture"))
                    .roles(Collections.singletonList(roleRepository.findByName(ERole.USER)))
                    .build());
        }
        User user = userService.getById(payload.getSubject());
        return jwtProvider.generateToken(user.getId());
    }
}
