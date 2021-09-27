package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Consulta;
import com.example.demo.entity.Persona;

public interface PersonaRepository extends Repository<Persona, String> {
	List<Persona>findAll();
	Persona findByPersonaId(String documentId);
	Persona save(Persona persona);
	void delete(Persona persona);
	
	@Query(value = "call consulta_examen(:age,:gender,:distrito); ", nativeQuery = true)
	List<Persona>filter(@Param("age")String age,@Param("gender")String gender,@Param("distrito")String distrito);
}
