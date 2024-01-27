package com.devpro.msdiet.service;

import com.devpro.msdiet.dto.PacienteDto;
import com.devpro.msdiet.mapper.PacienteMapper;
import com.devpro.msdiet.model.Paciente;
import com.devpro.msdiet.repository.PacienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PacienteService {

	private final PacienteRepository pacienteRepository;
	private final PacienteMapper pacienteMapper;

	public PacienteDto buscarPorId(Long id) {
		var paciente = pacienteRepository.buscaPacientePorId(id);
		return pacienteMapper.pacienteToDto(paciente);
	}

	public List<PacienteDto> listar() {
		var pacientes = pacienteRepository.findAll();

		var listaPacientes = pacientes.stream()
				.map(paciente -> pacienteMapper.pacienteToDto(paciente))
				.collect(Collectors.toList());

		return listaPacientes;
	}

	public PacienteDto editar(PacienteDto pacienteDto) {
		var paciente = pacienteRepository.buscaPacientePorId(pacienteDto.getId());
		paciente = pacienteMapper.dtoToPaciente(pacienteDto);
		pacienteRepository.save(paciente);

		return pacienteMapper.pacienteToDto(paciente);
	}

	public void salvar(PacienteDto pacienteDto) {
		var paciente = pacienteMapper.dtoToPaciente(pacienteDto);
		pacienteRepository.save(paciente);
	}

	public void deletar(Long id) {
		pacienteRepository.delete(pacienteRepository.buscaPacientePorId(id));
	}
}
