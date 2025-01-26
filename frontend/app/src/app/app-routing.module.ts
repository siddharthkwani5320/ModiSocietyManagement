import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { PaymentComponent } from './payment/payment.component';
import { UserComponent } from './user/user.component';
import { ClubhouseComponent } from './clubhouse/clubhouse.component';

const routes: Routes = [
  {path: '' , component: HomeComponent },
  {path: 'login' , component:LoginComponent},
  {path: 'clubhouse' , component:ClubhouseComponent},
  {path: 'utility' , component:LoginComponent},
  {path: 'payment' , component:PaymentComponent},
  {path: 'user' , component:UserComponent},
  {path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
