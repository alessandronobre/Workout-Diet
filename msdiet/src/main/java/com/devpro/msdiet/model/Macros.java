package com.devpro.msdiet.model;

import com.devpro.msdiet.enums.NivelAtividadeFisica;
import com.devpro.msdiet.enums.Objetivo;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Macros {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cod_macros")
	private Long id;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Objetivo objetivo;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private NivelAtividadeFisica nivelAtividadeFisica;
	
	@Column(name="data_calculo", nullable = false)
	private String dataCalculo;
	
	@Column(nullable = false)
	private String imc;
	
	@Column(name="taxa_metabolica_basal", nullable = false)
	private Integer tmb;
	
	@Column(name="gasto_calorico_total", nullable = false)
	private Integer gastoCaloricoTotal;

	@Column(name="calorias_treino", nullable = false)
	private Integer caloriasTreino;
	
	@Column(name="proteina_treino", nullable = false)
	private Integer proteinaTreino;
	
	@Column(name="carboidrato_treino", nullable = false)
	private Integer carboidratoTreino;
	
	@Column(name="gordura_treino", nullable = false)
	private Integer gorduraTreino;
	
	@Column(name="fibra_treino", nullable = false)
	private Integer fibraTreino;
	
	@Column(name="calorias_descanso", nullable = false)
	private Integer caloriasDescanso;
	
	@Column(name="proteina_descanso", nullable = false)
	private Integer proteinaDescanso;
	
	@Column(name="carboidrato_descanso", nullable = false)
	private Integer carboidratoDescanso;
	
	@Column(name="gordura_descanso", nullable = false)
	private Integer gorduraDescanso;
	
	@Column(name="fibra_descanso", nullable = false)
	private Integer fibraDescanso;

	@ManyToOne
	@JoinColumn(name="cod_paciente_fk", nullable = false)
	private Paciente paciente;

}
