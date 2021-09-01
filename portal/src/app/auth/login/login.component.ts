import { JwtHelperService } from '@auth0/angular-jwt';
import { LocalStorage as ls, LocalStorage } from 'src/app/utils/localstorage';
import { AuthService } from './../auth.service';
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public isLoading: boolean = false;
  public isRememberMe: boolean = false;
  public userEmail: any = '';
  public userPassword: any = '';
  public showPassword = false;

  constructor(private ref: ChangeDetectorRef, private router: Router, private authService: AuthService) {
  }

  ngOnInit() {
    const userCred: any = LocalStorage.getValue('userCred');
    if (userCred) {
      this.userEmail = userCred.n;
      this.userPassword = userCred.k;
    }
  }


  public onsubmit(form: any) {
    const helper = new JwtHelperService();
    this.isLoading = true;
    this.ref.detectChanges();
    this.authService.login({ username: form.value.email, password: form.value.password }).subscribe(res => {

      ls.setValue('currentUser', res.data.jwttoken);
      this.router.navigateByUrl('/');
      this.isLoading = false;
    }, err => {
      this.isLoading = false;
    });
  }

  // private isValidEmail(email): boolean {
  //   const pattern = new RegExp(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);
  //   return pattern.test(email);
  // }

  public onRemeberMeChanged() {
    if (!this.isRememberMe && LocalStorage.getValue('userCred')) {
      LocalStorage.removeValue('userCred');
    }
  }
}

