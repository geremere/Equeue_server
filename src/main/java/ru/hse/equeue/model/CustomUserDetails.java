package ru.hse.equeue.model;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Builder
public class CustomUserDetails implements UserDetails {
    private final String username;
    private final String userId;
    private final Collection<? extends GrantedAuthority> grantedAuthorities;

    public static CustomUserDetails fromUserEntityToCustomUserDetails(User user) {
        Collection<SimpleGrantedAuthority> newGrantedAuthorities = new ArrayList<>();

        for (Role role : user.getRoles()) {
            newGrantedAuthorities.add(new SimpleGrantedAuthority(role.getName().getValue()));
        }

        return CustomUserDetails.builder()
                .username(user.getName())
                .userId(user.getId())
                .grantedAuthorities(newGrantedAuthorities)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
