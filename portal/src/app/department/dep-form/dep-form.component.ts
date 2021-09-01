import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { DepartmentService } from '../department.service';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-dep-form',
  templateUrl: './dep-form.component.html',
  styleUrls: ['./dep-form.component.scss']
})
export class DepFormComponent implements OnInit {

  public depName: string = '';
  public depDes: string = '';
  public isEdit: boolean = false;
  public id: any;
  loading: boolean = true;
  constructor(private activatedRoute: ActivatedRoute, private depService: DepartmentService) {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.id = params.id;
      this.isEdit = Boolean(this.id);
      if (this.isEdit) {
        this.depService.getOne(this.id).subscribe((data: any) => {
          this.depDes = data.data.description;
          this.depName = data.data.name;
          this.loading = false;

        });
      } else {
        this.loading = false;

      }
    });
  }
  ngOnInit(): void {
  }

  handler(f: any) {
    if (this.isEdit) {
      this.update(f)
    } else {
      this.create(f);
    }
  }

  create(f: any) {
    this.depService.create({ name: f.form.value.name, description: f.form.value.des }).subscribe(console.log, console.log);
  }

  update(f: any) {
    this.depService.update({ name: f.form.value.name, description: f.form.value.des, id: this.id }).subscribe(console.log, console.log);
  }

}
