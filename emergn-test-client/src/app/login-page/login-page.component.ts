import { Component, OnInit } from '@angular/core';
import {LoginForm} from '../login-form';
import {UserService} from '../user.service';
import {TokenStorageService} from '../token-service.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  private loginForm: LoginForm = new LoginForm();
  private isLoginFailed = false;
  private isLoggedIn = false;
  private roles: string[] = [];
  private errorMessage = ``;

  constructor(private userService: UserService,
              private tokenStorageService: TokenStorageService) { }

  private onLogin() {
    console.log(this.loginForm);

    this.userService.attemptAuth(this.loginForm).subscribe(
      data => {
        this.tokenStorageService.saveToken(data.accessToken);
        this.tokenStorageService.saveUsername(data.username);
        this.tokenStorageService.saveAuthorities(data.authorities);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorageService.getAuthorities();
        this.reloadPage();
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  private onLogout() {
    this.tokenStorageService.signOut();
    this.reloadPage();
  }

  ngOnInit() {
    if (this.tokenStorageService.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorageService.getAuthorities();
    }
  }

  private reloadPage() {
    window.location.reload();
  }

}
