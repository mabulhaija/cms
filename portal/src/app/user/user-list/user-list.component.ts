import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { UserService } from '../user.service';


@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

  @ViewChild(MatSort) sort: any;
  @ViewChild(MatPaginator) paginator: any;

  elemData: any[] = [];
  loading: boolean = true;


  constructor(private depService: UserService, private router: Router) {
    this.depService.getAll().subscribe((data: any) => {
      this.elemData = data.data;
      this.dataSource = new MatTableDataSource(this.elemData);

      this.loading = false;
    }, console.log);
  }

  ngOnInit(): void {
  }
  displayedColumns: string[] = ['id', 'name', 'email', 'actions'];
  dataSource = new MatTableDataSource(this.elemData);


  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;

  }

  delete(e: any, id: any) {
    this.depService.delete(id).subscribe((res) => {
      location.reload();
    }, console.log);
  }
}
