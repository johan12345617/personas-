import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators  } from '@angular/forms';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { Consulta } from 'src/app/model/consulta';
import { Persona } from 'src/app/model/persona';
import { PersonaService } from 'src/app/services/persona.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  edad:string=""
  distrito:string=""
  gender:string=""
  personas:Persona[]=[]

  constructor(private service:PersonaService,private router:Router,private fb: FormBuilder){
  }

  ngOnInit(): void {
    this.getPersonas();
  }

  getPersonas(){
    this.service.getPersonas().subscribe(data=>{
      this.personas=data
    })
  }

  goToEdit(persona:Persona){
    localStorage.setItem("id",persona.personaId.toString())
    this.router.navigate(["editar"])
  }

  delete(persona:Persona){
    this.service.deletePersona(persona.personaId).subscribe(data=>{
      this.getPersonas();
    })
  }

  generarReporte(){
    this.service.getReportPersonas().subscribe(response => this.downLoadFile(response, "application/ms-excel"));
  }

  downLoadFile(data: any, type: string) {
    let blob = new Blob([data], { type: type});
    let url = window.URL.createObjectURL(blob);
    let a = document.createElement("a");
            a.href = url;
            a.download = 'reporte.xlsx';
            a.click();
  }

  filtrar(){
    let consulta= new Consulta()
    consulta.age=this.edad
    consulta.distrito=this.distrito
    consulta.gender=this.gender
    console.log(consulta);
    this.service.getPersonaFilter(consulta).subscribe(data=>{
      console.log(data)
      this.personas=data
    })
  }

}
