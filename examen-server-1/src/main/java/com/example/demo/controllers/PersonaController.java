package com.example.demo.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Consulta;
import com.example.demo.entity.Persona;
import com.example.demo.services.PersonaService;
import com.example.demo.util.UserExcelExporter;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping({"/personas"})

public class PersonaController {
	@Autowired
	PersonaService service;
	
	@GetMapping	
	public List<Persona>listar(){
		return service.listar();
	}
	
	@PostMapping
	public Persona add(@RequestBody Persona persona) {
		return service.add(persona);
	}
	
	@GetMapping(path={"/{id}"})
	public Persona getPersona(@PathVariable("id")String id){
		return service.getPersonaById(id);
	}
	
	@PutMapping(path= {"/{id}"})
	public Persona editar(@RequestBody Persona persona,@PathVariable("id")String id){
		persona.setPersonaId(id);
		return service.edit(persona); 
	}
	
	@DeleteMapping(path={"/{consulta}"})
	public Persona delete(@PathVariable("consulta")String id){
		return service.delete(id);
	}
	
	@PostMapping(path={"/filtro"})
	public List<Persona> asd(@RequestBody Consulta consulta){
		return service.filter(consulta);
	}
	
	
    @GetMapping(path={"/export/excel"})
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<Persona> personas = service.listar();
         
        UserExcelExporter excelExporter = new UserExcelExporter(personas);
         
        excelExporter.export(response);    
    } 
	
}
