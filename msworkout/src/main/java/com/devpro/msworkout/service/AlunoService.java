package com.devpro.msworkout.service;

import com.devpro.msworkout.dto.AlunoDto;
import com.devpro.msworkout.mapper.AlunoMapper;
import com.devpro.msworkout.repository.AlunoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;

    public AlunoDto buscaPorId(Long id) {
        var aluno = alunoRepository.buscaAlunoPorId(id);
        return alunoMapper.alunoToDto(aluno);
    }

    public List<AlunoDto> listar() {
        var alunos = alunoRepository.findAll();

        var listaAlunos = alunos.stream()
                .map(aluno -> alunoMapper.alunoToDto(aluno))
                .collect(Collectors.toList());

        return listaAlunos;
    }

    public AlunoDto editar(AlunoDto alunoDto) {
        var aluno = alunoRepository.buscaAlunoPorId(alunoDto.getId());
        aluno = alunoMapper.dtoToAluno(alunoDto);
        alunoRepository.save(aluno);

        return alunoMapper.alunoToDto(aluno);
    }

    public AlunoDto salvar(AlunoDto alunoDto) {
        var aluno = alunoRepository.save(alunoMapper.dtoToAluno(alunoDto));
        return alunoMapper.alunoToDto(aluno);
    }

    public void deletar(Long id) {
        alunoRepository.deleteById(id);
    }
}
