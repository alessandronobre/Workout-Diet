package com.devpro.msdiet.controller;

import com.devpro.msdiet.dto.PacienteDto;
import com.devpro.msdiet.dto.TesteDto;
import com.devpro.msdiet.repository.TesteRepository;
import com.devpro.msdiet.service.PacienteService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

	private final PacienteService pacienteService;

	private final TesteRepository testeRepository;

	@GetMapping("/{pageNumber}/{pageSize}")
	public Page<TesteDto> listarPacientes(@PathVariable int pageNumber, @PathVariable int pageSize) {
		return testeRepository.teste(pageNumber,  pageSize);
	}

//	@GetMapping
//	public List<PacienteDto> listarPacientes() {
//		return pacienteService.listar();
//	}

	@GetMapping("/{id}")
	public PacienteDto buscar(@PathVariable Long id) {
		return pacienteService.buscarPorId(id);
	}

	@PutMapping
	public PacienteDto editar(@RequestBody PacienteDto pacienteDto) {
		return pacienteService.editar(pacienteDto);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void cadastrar(@RequestBody PacienteDto pacienteDto) {
		pacienteService.salvar(pacienteDto);

	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		pacienteService.deletar(id);
	}
}