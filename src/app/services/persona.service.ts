import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Persona } from '../model/persona';
import { Consulta } from '../model/consulta';
@Injectable({
  providedIn: 'root'
})
export class PersonaService {

  AUTH_SERVER: string = 'http://localhost:8080/examen';
  persona: Persona[] = [];
  
  constructor(private http:HttpClient) { 
  }

  getPersonas(){
    return this.http.get<Persona[]>(this.AUTH_SERVER+'/personas')
  }

  addPersonas(persona:Persona){
    return this.http.post(this.AUTH_SERVER+'/personas',persona)
  }

  getPersonaId(id:string){
    return this.http.get<Persona>(this.AUTH_SERVER+'/personas/'+id)
  }

  updatePersona(persona:Persona){
    return this.http.put<Persona>(this.AUTH_SERVER+'/personas/'+persona.personaId,persona)
  }

  deletePersona(id:string){
    return this.http.delete<Persona>(this.AUTH_SERVER+'/personas/'+id)
  }

  getReportPersonas() {
    return this.http.get<any>(this.AUTH_SERVER+'/personas/export/excel',{
      responseType: 'arraybuffer' as 'json'
    });
  }

  getPersonaFilter(consulta:Consulta){
    return this.http.post<Persona[]>(this.AUTH_SERVER+'/personas/filtro',consulta)
  }
}
