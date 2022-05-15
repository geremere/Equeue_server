package ru.hse.equeue.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hse.equeue.dto.UserInQueueDto;
import ru.hse.equeue.model.QueueUserBinding;

@Configuration
public class ModelMapperConfig {

    @Bean(name = "modelMapper")
    public ModelMapper defaultObjectMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        return mapper;
    }

    @Bean(name = "queueMapper")
    public ModelMapper queueObjectMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        mapper.createTypeMap(QueueUserBinding.class, UserInQueueDto.class)
                .addMapping(source->source.getStatus().getName(),UserInQueueDto::setStatus)
                .addMapping(source->source.getUser().getName(),UserInQueueDto::setName)
                .addMapping(source->source.getUser().getPhotoUrl(),UserInQueueDto::setPhotoUrl)
                .addMapping(source->source.getUser().getId(),UserInQueueDto::setId);
        return mapper;
    }
}
