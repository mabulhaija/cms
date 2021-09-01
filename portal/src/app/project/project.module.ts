import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ProjListComponent } from './proj-list/proj-list.component';
import { ProjFormComponent } from './proj-form/proj-form.component';
import { MatChipsModule } from '@angular/material/chips';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ProjectService } from './project.service';
import { UserService } from '../user/user.service';



const routes: Routes = [
  {
    path: '',
    component: ProjListComponent
  },
  {
    path: 'create',
    component: ProjFormComponent
  },
  {
    path: 'update/:id',
    component: ProjFormComponent
  },

  {
    path: '**',
    redirectTo: ''
  }
];

@NgModule({
  declarations: [
    ProjListComponent,
    ProjFormComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatTableModule, MatPaginatorModule,
    MatInputModule,
    MatChipsModule,
    MatAutocompleteModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    HttpClient,
    ProjectService,
    UserService
  ]
})
export class ProjectModule { }
