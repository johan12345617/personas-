import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PersonaService } from 'src/app/services/persona.service';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  form: FormGroup;
  file:any;

  constructor(private fb: FormBuilder,private service:PersonaService,private router: Router){
    this.form = this.fb.group({
      documentType:['',Validators.required],
      documentId: ['', Validators.email],
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

  ngOnInit(): void {
  }

  savePersona(){
    this.service.addPersonas(this.form.value).subscribe(res=>{
      console.log("persona agregada")
      this.router.navigateByUrl('/lista');
    })
  }

  onFileSelected(event:any) {
    const file:File = event.target.files[0];
    if (file) {
      this.file=file
      console.log(file)
    }
  }

}
