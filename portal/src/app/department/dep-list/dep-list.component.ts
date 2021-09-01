import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { DepartmentService } from '../department.service';




@Component({
  selector: 'app-dep-list',
  templateUrl: './dep-list.component.html',
  styleUrls: ['./dep-list.component.scss']
})
export class DepListComponent implements OnInit {
  @ViewChild(MatSort) sort: any;
  @ViewChild(MatPaginator) paginator: any;
  elemData: any[] = [];
  loading: boolean = true;


  constructor(private depService: DepartmentService) {
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
