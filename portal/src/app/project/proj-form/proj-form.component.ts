import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { MatChipInputEvent } from '@angular/material/chips';
import { ActivatedRoute, Params } from '@angular/router';
import { Observable } from 'rxjs';
import { map, startWith, throwIfEmpty, window } from 'rxjs/operators';
import { UserService } from 'src/app/user/user.service';
import { ProjectService } from '../project.service';
@Component({
  selector: 'app-proj-form',
  templateUrl: './proj-form.component.html',
  styleUrls: ['./proj-form.component.scss']
})
export class ProjFormComponent {
  isEdit = false;
  projName = '';
  projDes = '';
  selectable = true;
  removable = true;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  fieldCtrl = new FormControl();
  filteredValues!: Observable<any[]>;
  values: any[] = [];
  allValues: any[] = ['Mohammad', 'Rana', 'Omar', 'Bana', 'Dania'];
  @ViewChild('valueInput')
  valueInput!: ElementRef<HTMLInputElement>;
  public id: any;
  loading: boolean = true;
  constructor(private activatedRoute: ActivatedRoute,
    private depService: ProjectService,
    private userService: UserService) {
    this.filteredValues = this.fieldCtrl.valueChanges.pipe(
      startWith(null),
      map((value: any | null) => value ? this._filter(value) : this.allValues.slice()));
    this.activatedRoute.params.subscribe((params: Params) => {
      this.id = params.id;
      this.isEdit = Boolean(this.id);
      this.userService.getAll().subscribe((data: any) => {
        this.allValues = data.data;

        if (this.isEdit) {
          this.depService.getOne(this.id).subscribe((data: any) => {
            this.projDes = data.data.description;
            this.projName = data.data.name;
            this.depService.getProjectUsers(this.id).subscribe((data: any) => {
              this.values = data.data;
              this.loading = false;
            }, err => {
              this.values = [];
            })

          });
        } else {
          this.loading = false;

        }
      });



    });
  }
  add(event: MatChipInputEvent): void {
    const value = (event.value);

    if (value) {
      this.values.push(value);
    }

    // Clear the input value
    event.chipInput!.clear();

    this.fieldCtrl.setValue(null);
  }

  remove(value: any): void {
    const index = this.values.indexOf(value);

    if (index >= 0) {
      this.depService.removeUserFromProject({ projId: this.id, userId: value.userId }).subscribe(() => {
        this.values.splice(index, 1);

      })
    }
  }

  handler(f: any) {
    if (this.isEdit) {
      this.update(f)
    } else {
      this.create(f);
    }
  }

  create(f: any) {
    this.depService.create({ name: f.form.value.name, description: f.form.value.des }).subscribe(() => {

    }, console.log);
  }

  update(f: any) {
    this.depService.update({ name: f.form.value.name, description: f.form.value.des, id: this.id }).subscribe(console.log, console.log);
  }


  selected(event: MatAutocompleteSelectedEvent): void {
    this.depService.assignUserToProject({ projId: Number(this.id), userId: event.option.value.id }).subscribe(() => {
      this.values.push(event.option.value);
      this.valueInput.nativeElement.value = '';
      this.fieldCtrl.setValue(null);
    })

  }

  private _filter(value: any): string[] {
    const filterValue = value.name;

    return this.allValues.filter(value => value.name.includes(filterValue));
  }

  ngOnInit(): void {
  }

}
