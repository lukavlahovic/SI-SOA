package com.broker.broker.mappers;

import com.broker.broker.domain.Servis;
import com.broker.broker.domain.SlozenServis;
import com.broker.broker.model.ServiceDTO;
import com.broker.broker.model.SlozenDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SlozenMapper {

    SlozenMapper instance = Mappers.getMapper(SlozenMapper.class);

    SlozenDTO slozentoSlozenDTON(SlozenServis slozenServis);

    SlozenServis slozenDTOtoslozen(SlozenDTO slozenDTO);

}
