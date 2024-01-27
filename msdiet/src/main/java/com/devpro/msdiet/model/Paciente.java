package com.devpro.msdiet.model;

import com.devpro.msdiet.enums.Genero;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cod_paciente")
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Genero genero;

	@Column(nullable = false)
	private Integer idade;

	@Column(nullable = false)
	private Integer altura;

	@Column(nullable = false)
	private Integer peso;

	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Macros> historicoMacros;

}
