import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { LocalStorage } from '../utils/localstorage';
const header = new HttpHeaders({
  'Content-Type': 'application/json',
  'Authorization': `Bearer ${LocalStorage.getValue('currentUser')}`
});
const url = environment.url + 'departments';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  constructor(private http: HttpClient) { }

  create(params: any) {
    return this.http.post(url,
      { name: params.name, description: params.description },
      { headers: header })
  }

  update(params: any) {
    return this.http.put(url + '/' + params.id,
      { name: params.name, description: params.description },
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
