package ru.hse.equeue.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ERole {
    ADMIN("ADMIN"),
    OWNER("OWNER"),
    USER("USER");

    private final String value;

}
