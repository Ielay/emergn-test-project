import { Component, OnInit } from '@angular/core';
import {UserService} from '../user.service';
import {Observable} from 'rxjs';
import {UserData} from '../user-data';

@Component({
  selector: 'app-users-table',
  templateUrl: './users-table.component.html',
  styleUrls: ['./users-table.component.css']
})
export class UsersTableComponent implements OnInit {
  private users: Observable<UserData[]>;

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.reloadData();
  }

  private reloadData() {
    this.users = this.userService.getAll();
  }

}
