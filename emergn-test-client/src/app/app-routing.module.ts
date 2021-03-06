import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UsersTableComponent} from './users-table/users-table.component';
import {LoginPageComponent} from './login-page/login-page.component';
import {RegistrationPageComponent} from './registration-page/registration-page.component';

const routes: Routes = [
  {path: 'users', component: UsersTableComponent},
  {path: 'login', component: LoginPageComponent},
  {path: 'register', component: RegistrationPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
