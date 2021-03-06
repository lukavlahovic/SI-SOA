package com.broker.broker.mappers;

import com.broker.broker.domain.Provajder;
import com.broker.broker.domain.UserBroker;
import com.broker.broker.model.ProvajderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProvajderMapper {

    ProvajderMapper instance = Mappers.getMapper(ProvajderMapper.class);

    @Mapping(source = "username", target = "name")
    ProvajderDTO provajderToprovajderDTO(UserBroker provajder);

    @Mapping(source = "name", target = "username")
    UserBroker provajderDTOtoprovajder(ProvajderDTO provajderDTO);

}
