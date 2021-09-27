import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Persona } from 'src/app/model/persona';
import { PersonaService } from 'src/app/services/persona.service';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
  persona: Persona = new Persona;
  form: FormGroup;
  constructor(private fb: FormBuilder,private service:PersonaService,private router: Router){
    this.form = this.fb.group({
      documentType:['',Validators.required],
      documentId: ['', Validators.required],
      name:['',Validators.required],
      lastName:['',Validators.required],
      birthdayDate: ['', Validators.required],
      departamento:['',Validators.required],
      provincia:['',Validators.required],
      distrito:['',Validators.required],
      photo:['',Validators.required],
      gender:['',Validators.required],
      age:['',Validators.required]
    });
  }

  //carga datos de persona
  ngOnInit(): void {
    this.loadPersona()
  }

  loadPersona(){
    let id=localStorage.getItem("id")|| '{}';
    this.service.getPersonaId(id).subscribe(data=>{
      this.persona=data,
      console.log(this.persona.personaId)
      this.form.setValue({
        documentType:this.persona.documentType,
        documentId:this.persona.documentId,
        name:this.persona.name,
        lastName:this.persona.lastName,
        birthdayDate:this.persona.birthdayDate,
        departamento:this.persona.departamento,
        provincia:this.persona.provincia,
        distrito:this.persona.distrito,
        photo:this.persona.photo,
        gender:this.persona.gender,
        age:this.persona.age
      });
    })
  }

  editPersona(){
    this.persona=this.form.value
    this.persona.personaId=localStorage.getItem("id")|| '{}';
    console.log(this.persona.personaId)
    this.service.updatePersona(this.persona).subscribe(res=>{
      console.log("persona editada")
      this.router.navigateByUrl('/lista');
    })
  }

  onUpload(e:any){

  }
}
