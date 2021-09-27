package com.example.demo.services;

import java.util.List;
import com.example.demo.entity.Persona;
import com.example.demo.entity.Consulta;

public interface PersonaService {
	List<Persona>listar();
	Persona getPersonaById(String personaId);
	Persona add(Persona persona);
	Persona edit(Persona persona);
	Persona delete(String documentId);
	List<Persona> filter(Consulta consulta);
}