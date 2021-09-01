import { JwtHelperService } from '@auth0/angular-jwt';
import { Params } from '@angular/router';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }


  public login(param: any): Observable<any> {
    const header = new HttpHeaders({
      'Authorization': 'Baerer',
      'Content-Type': 'application/json'
    });
    return this.http.post(environment.url + `login`, param, { headers: header });
  }
  public signout(id: any, refreshToken: any) {
    // const header: HttpHeaders = new HttpHeaders({
    //   'Content-Type': 'application/x-www-form-urlencoded'
    // });
    // const body = (new HttpParams().append('refreshTokenId', refreshToken).append('tokenId', id)).toString();
  }

}
