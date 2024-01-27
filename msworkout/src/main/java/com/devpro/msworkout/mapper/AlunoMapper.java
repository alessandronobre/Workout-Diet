package com.devpro.msworkout.mapper;

import com.devpro.msworkout.dto.AlunoDto;
import com.devpro.msworkout.model.Aluno;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    AlunoDto alunoToDto(Aluno aluno);

    Aluno dtoToAluno(AlunoDto AlunoDto);
}
