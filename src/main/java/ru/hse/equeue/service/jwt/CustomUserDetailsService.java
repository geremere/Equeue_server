package ru.hse.equeue.service.jwt;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.hse.equeue.model.CustomUserDetails;
import ru.hse.equeue.model.User;
import ru.hse.equeue.service.UserService;


@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userService.getById(userId);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
    }
}
