package com.provajder1.provajder1.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddMapper {

    AddMapper istance = Mappers.getMapper(AddMapper.class);
}
