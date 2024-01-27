package com.devpro.msdiet.repository;

import com.devpro.msdiet.dto.TesteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class TesteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Page<TesteDto> teste(int pageNumber, int pageSize) {
        String sql = "select nome, email from paciente";

        int offset = pageNumber * pageSize;

        List<TesteDto> items = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TesteDto.class));

        // Criar uma lista de sublista com base no pageSize e pageNumber
        List<TesteDto> subList = items.subList(offset, Math.min(offset + pageSize, items.size()));

        return new PageImpl<>(subList, PageRequest.of(pageNumber, pageSize), items.size());

    }

}
