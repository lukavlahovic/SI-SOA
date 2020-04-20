package com.broker.broker.mappers;


import com.broker.broker.domain.Servis;
import com.broker.broker.model.ServiceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ServiceMapper {

    ServiceMapper instance = Mappers.getMapper(ServiceMapper.class);

    ServiceDTO serviceToServiceDTO(Servis servis);

    Servis serviceDTOtoService(ServiceDTO serviceDTO);

}
