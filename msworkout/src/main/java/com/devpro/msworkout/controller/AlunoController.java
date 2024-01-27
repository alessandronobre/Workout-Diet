package com.devpro.msworkout.controller;

import com.devpro.msworkout.dto.AlunoDto;
import com.devpro.msworkout.service.AlunoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://127.0.0.1:5500")
@AllArgsConstructor
@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    @GetMapping
    public List<AlunoDto> listarPacientes() {
        return alunoService.listar();
    }

    @GetMapping("/{id}")
    public AlunoDto buscar(@PathVariable Long id) {
        return alunoService.buscaPorId(id);
    }

    @PutMapping
    public AlunoDto editar(@RequestBody AlunoDto alunoDto) {
        return alunoService.editar(alunoDto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AlunoDto cadastrar(@RequestBody AlunoDto alunoDto) {
        return alunoService.salvar(alunoDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        alunoService.deletar(id);
    }
}
