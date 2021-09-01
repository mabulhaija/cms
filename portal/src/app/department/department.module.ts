import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DepListComponent } from './dep-list/dep-list.component';
import { RouterModule, Routes } from '@angular/router';
import { DepFormComponent } from './dep-form/dep-form.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatInputModule } from '@angular/material/input';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { DepartmentService } from './department.service';
import { FormsModule } from '@angular/forms';




const routes: Routes = [
  {
    path: '',
    component: DepListComponent
  },
  {
    path: 'create',
    component: DepFormComponent
  },
  {
    path: 'update/:id',
    component: DepFormComponent
  },

  {
    path: '**',
    redirectTo: ''
  }
];

@NgModule({
  declarations: [
    DepListComponent,
    DepFormComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    MatTableModule, MatPaginatorModule,
    MatInputModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    DepartmentService,
    HttpClient
  ]
})
export class DepartmentModule { }
