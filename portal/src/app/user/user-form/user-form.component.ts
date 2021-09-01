import { ENTER, COMMA } from '@angular/cdk/keycodes';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { MatChipInputEvent } from '@angular/material/chips';
import { ActivatedRoute, Params } from '@angular/router';
import { Observable } from 'rxjs';
import { startWith, map } from 'rxjs/operators';
import { DepartmentService } from 'src/app/department/department.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent implements OnInit {

  isEdit = false;
  empName = '';
  empRole = '';
  selectable = true;
  removable = true;
  empEmail = '';
  empUsername = '';
  empPassword = '';
  empDep = '';
  deps: any = [];
  @ViewChild('valueInput')
  valueInput!: ElementRef<HTMLInputElement>;
  public id: any;
  loading: boolean = true;
  constructor(private activatedRoute: ActivatedRoute, private userService: UserService,
    private depService: DepartmentService) {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.id = params.id;
      this.isEdit = Boolean(this.id);
      this.depService.getAll().subscribe((deps: any) => {
        this.deps = deps.data;
        if (this.isEdit) {
          this.userService.getOne(this.id).subscribe((data: any) => {
            this.empName = data.data.name;
            this.empUsername = data.data.userName;
            this.empPassword = data.data.password;
            this.empEmail = data.data.email;
            this.empRole = data.data.role;
            this.empDep = data.data.departmentId;
            this.loading = false;

          });
        } else {
          this.loading = false;

        }
      });

    });
  }

  ngOnInit(): void {
  }

  handler(f: any) {
    const data = f.form.value;

    if (this.isEdit) {
      this.update(data)
    } else {
      this.create(data);
    }

  }

  create(data: any) {
    const dep = this.deps.filter((dep: any) => dep.id == data.dep)[0];
    this.userService.create({
      name: data.name,
      userName: data.username,
      email: data.email,
      password: data.password,
      departmentName: dep.name,
      departmentId: dep.id,
      role: this.empRole

    }).subscribe(console.log, console.log);
  }

  update(data: any) {
    const dep = this.deps.filter((dep: any) => dep.id == data.dep)[0];
    this.userService.update({
      name: data.name,
      userName: data.username,
      email: data.email,
      password: '',
      departmentName: dep.name,
      departmentId: dep.id,
      role: this.empRole,
      id: this.id
    }).subscribe(console.log, console.log);
  }


}
