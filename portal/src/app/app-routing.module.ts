import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { WrapperComponent } from './components/wrapper/wrapper.component';
import { AuthGuard } from './auth/auth.guard.service';

const routes: Routes = [
  {
    path: '',
    component: WrapperComponent,
    canActivate:[AuthGuard],
    children: [
      {
        path: '',
        component: WelcomeComponent,
        // canActivate: [AuthGuard],
      }, {
        path: 'department',
        loadChildren: () => import("./department/department.module").then(m => m.DepartmentModule)
      },
      {
        path: 'user',
        loadChildren: () => import("./user/user.module").then(m => m.UserModule)
      },
      {
        path: 'project',
        loadChildren: () => import("./project/project.module").then(m => m.ProjectModule)
      }]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: '**',
    redirectTo: '',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    useHash: true
  })],
  exports: [RouterModule]
})

export class AppRoutingModule { }
