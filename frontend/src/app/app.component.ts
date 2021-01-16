import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CategoryService } from './common/category.service';
import { UserService } from './common/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'OnlineShop';

  constructor( private router: Router, private userService: UserService ) { }

  ngOnInit() {
    this.userService.setBaseUrl(document.URL);
    this.router.navigate(['/login']);
  }
}
