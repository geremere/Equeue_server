package ru.hse.equeue.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.hse.equeue.dto.UserDto;
import ru.hse.equeue.model.User;

@Component
@RequiredArgsConstructor
public class UserConverter {
    private final ModelMapper modelMapper;

    public UserDto toDto(User user){
        return modelMapper.map(user, UserDto.class);
    }
}
