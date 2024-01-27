package com.devpro.msdiet.repository;

import com.devpro.msdiet.model.Macros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MacrosRepository extends JpaRepository<Macros, Long> {

	@Query(value="SELECT * " +
			"FROM MACROS " +
			"WHERE COD_PACIENTE_FK = ?1 ", nativeQuery = true)
	List<Macros> buscarMacros(Long id);
}
