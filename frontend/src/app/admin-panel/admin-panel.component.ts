import { Component, OnInit } from '@angular/core';
import { AdminService } from '../common/admin.service';
import { CategoryService } from '../common/category.service';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {

  logs: any;
  addCats = ["Vehicles", "Electronics", "Mobile Devices", "Real Estates", "My Home", "Beauty and Health", "Computers", "Games and Toys", "Video Games", "Business and Industry", "Arts", "Animals"];

  constructor( private adminService: AdminService, private categoryService: CategoryService ) { }

  ngOnInit() {
    this.adminService.getLogs();
    this.adminService.logsSuccess.subscribe((res) => {
      this.logs = res;
    });
  }

  fillCategories() {
    for (let c of this.addCats) {
      setTimeout(() => this.categoryService.uploadCategory(c), 20);
    }
  }
}
