import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddComponent } from './persona/add/add.component';
import { EditComponent } from './persona/edit/edit.component';
import { ListComponent } from './persona/list/list.component';


const routes: Routes = [
  { path: '', redirectTo: 'lista', pathMatch: 'full' },
  //{ path: '**', pathMatch   : 'full', component: NotFoundComponent},
  { path: 'lista', component: ListComponent },
  { path: 'agregar', component: AddComponent },
  { path: 'editar', component: EditComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
