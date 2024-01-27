package com.devpro.msdiet.dto;

import com.devpro.msdiet.enums.Genero;
import lombok.Data;

import java.util.List;

@Data
public class PacienteDto {

	private Long id;
	private String nome;
	private String email;
	private Genero genero;
	private Integer idade;
	private Integer altura;
	private Integer peso;
	private List<MacrosDto> historicoMacros;

}