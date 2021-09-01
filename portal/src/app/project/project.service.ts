import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { LocalStorage } from '../utils/localstorage';
const header = new HttpHeaders({
  'Content-Type': 'application/json',
  'Authorization': `Bearer ${LocalStorage.getValue('currentUser')}`
});
const url = environment.url + 'projects';
const project_user_url = environment.url + 'project-users';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {


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

  getProjectUsers(id: any) {
    return this.http.get(project_user_url + '/' + id, { headers: header });
  }

  assignUserToProject(params: any) {
    return this.http.post(project_user_url + '/' + params.projId,
      { projectId: params.projId, userId: params.userId },
      { headers: header })
  }

  removeUserFromProject(params: any) {
    return this.http.delete(project_user_url + '/' + params.projId + '/' + params.userId, { headers: header });

  }
}
