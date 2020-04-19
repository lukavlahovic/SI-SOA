package com.broker.broker.mappers;

import com.broker.broker.domain.Endpoint;
import com.broker.broker.model.EndpointDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EndpointMapper {
    EndpointMapper instance = Mappers.getMapper(EndpointMapper.class);

    Endpoint endpointDTOToEndpoint(EndpointDTO endpointDTO);
}
