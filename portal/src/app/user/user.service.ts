import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { LocalStorage } from '../utils/localstorage';
const header = new HttpHeaders({
  'Content-Type': 'application/json',
  'Authorization': `Bearer ${LocalStorage.getValue('currentUser')}`
});
const url = environment.url + 'users';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  constructor(private http: HttpClient) { }

  create(params: any) {
    return this.http.post(url, params,
      { headers: header })
  }

  update(params: any) {
    return this.http.put(url + '/' + params.id, params,
      { headers: header })
  }

  getAll() {
    return this.http.get(url, { headers: header });
  }

  getOne(id: any) {
    return this.http.get(url + '/' + id, { headers: header });
  }

  delete(id: any) {
    return this.http.delete(url + '/' + id, { headers: header });
  }
}
