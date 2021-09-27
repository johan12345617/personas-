package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Consulta;
import com.example.demo.entity.Persona;
import com.example.demo.repositories.PersonaRepository;

@Service
public class PersonaServiceImp implements PersonaService{
	
	@Autowired
	private PersonaRepository repository;
	
	@Override
	public List<Persona> listar(){
		return repository.findAll();
	}
	
	@Override
	public Persona getPersonaById(String personaId){
		return repository.findByPersonaId(personaId);
	}
	
	@Override
	public Persona add(Persona persona){
		return repository.save(persona);
	}
	
	@Override
	public Persona edit(Persona persona){
		return repository.save(persona);
	}
	
	@Override
	public Persona delete(String personaId) {
		Persona p = repository.findByPersonaId(personaId);
		if(p!=null) {
			repository.delete(p);
		}
		return p;
	}

	@Override
	public List<Persona> filter(Consulta consulta) {
		return repository.filter(consulta.getAge(),consulta.getGender(),consulta.getDistrito());
	}
}
