import { Component, OnInit } from '@angular/core';
import {RegistrationForm} from '../registration-form';
import {UserService} from '../user.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})
export class RegistrationPageComponent implements OnInit {
  private userService: UserService;
  private registrationForm: RegistrationForm = new RegistrationForm();

  constructor(userService: UserService) {
    this.userService = userService;
  }

  private onRegisterClicked() {
    console.log(this.registrationForm);

    this.userService.registerUser(this.registrationForm)
      .subscribe(data => console.log(data), error => console.log(error));
  }

  ngOnInit() {
  }
}
