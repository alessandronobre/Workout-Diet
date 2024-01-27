package com.devpro.msdiet.mapper;

import com.devpro.msdiet.dto.PacienteDto;
import com.devpro.msdiet.model.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    @Mapping(target = "historicoMacros", source = "historicoMacros")
    PacienteDto pacienteToDto(Paciente paciente);

    @Mapping(target = "historicoMacros", ignore = true)
    Paciente dtoToPaciente(PacienteDto pacienteDto);

}
