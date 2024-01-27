package com.devpro.msdiet.repository;

import com.devpro.msdiet.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	@Query(value = "SELECT * FROM PACIENTE WHERE COD_PACIENTE = ?", nativeQuery = true)
	Paciente buscaPacientePorId(Long id);

}