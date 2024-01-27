package com.devpro.msdiet.dto;

import com.devpro.msdiet.enums.NivelAtividadeFisica;
import com.devpro.msdiet.enums.Objetivo;
import lombok.Data;

@Data
public class MacrosDto {

	private Long id;
	private Objetivo objetivo;
	private NivelAtividadeFisica nivelAtividadeFisica;
	private String dataCalculo;
	private String imc;
	private Integer tmb;
	private Integer gastoCaloricoTotal;
	private Integer caloriasTreino;
	private Integer proteinaTreino;
	private Integer carboidratoTreino;
	private Integer gorduraTreino;
	private Integer fibraTreino;
	private Integer caloriasDescanso;
	private Integer proteinaDescanso;
	private Integer carboidratoDescanso;
	private Integer gorduraDescanso;
	private Integer fibraDescanso;
	private PacienteDto paciente;

}
