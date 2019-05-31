import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {RegistrationForm} from './registration-form';
import {UserData} from './user-data';
import {LoginForm} from './login-form';
import {JwtResponse} from './jwt-response';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = `http://localhost:8080/emergn-test-project/api/`;

  constructor(private httpClient: HttpClient) {
  }

  public getAll(): Observable<UserData[]> {
    return this.httpClient.get<UserData[]>(this.baseUrl.concat(`users`));
  }

  public registerUser(newUserRegistrationForm: RegistrationForm): Observable<any> {
    return this.httpClient.post<any>(this.baseUrl.concat(`register`), newUserRegistrationForm);
  }

  public attemptAuth(loginForm: LoginForm): Observable<JwtResponse> {
    return this.httpClient.post<JwtResponse>(this.baseUrl.concat(`login`), loginForm);
  }
}
