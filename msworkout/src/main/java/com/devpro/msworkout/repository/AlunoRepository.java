package com.devpro.msworkout.repository;

import com.devpro.msworkout.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query(value = "SELECT * FROM ALUNO WHERE COD_ALUNO = ?", nativeQuery = true)
    Aluno buscaAlunoPorId(Long id);
}
