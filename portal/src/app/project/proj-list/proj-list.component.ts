import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ProjectService } from '../project.service';

export interface PeriodicElement {
  name: string;
  id: number;
  numOfEmployees: number;
  symbol: string;
  actions?: any;
}


@Component({
  selector: 'app-proj-list',
  templateUrl: './proj-list.component.html',
  styleUrls: ['./proj-list.component.scss']
})
export class ProjListComponent implements OnInit {
  @ViewChild(MatSort) sort: any;
  @ViewChild(MatPaginator) paginator: any;

  elemData: any[] = [];
  loading: boolean = true;


  constructor(private depService: ProjectService) {
    this.depService.getAll().subscribe((data: any) => {
      this.elemData = data.data;
      this.dataSource = new MatTableDataSource(this.elemData);

      this.loading = false;
    }, console.log);
  }

  ngOnInit(): void {
  }
  displayedColumns: string[] = ['id', 'name', 'actions'];
  dataSource = new MatTableDataSource(this.elemData);


  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;

  }

  delete(e: any, id: any) {
    this.depService.delete(id).subscribe((res) => {
      location.reload();

    }, console.log
    );
  }
}
