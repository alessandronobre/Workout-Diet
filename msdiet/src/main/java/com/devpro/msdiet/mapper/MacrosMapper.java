package com.devpro.msdiet.mapper;

import com.devpro.msdiet.dto.MacrosDto;
import com.devpro.msdiet.model.Macros;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MacrosMapper {

    MacrosDto macrosToDto(Macros macros);
    Macros dtoToMacros(MacrosDto macrosDto);
}
