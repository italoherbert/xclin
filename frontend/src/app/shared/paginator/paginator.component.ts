import { Component, Input, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-paginator',
  templateUrl: './paginator.component.html',
  styleUrls: ['./paginator.component.css']
})
export class PaginatorComponent {

  @Input("tableDataSource") tableDataSource! : MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator! : MatPaginator;

  ngAfterViewInit() {
    this.paginator._intl.itemsPerPageLabel = "Ítens por página"
    this.tableDataSource.paginator = this.paginator;
  }

}
