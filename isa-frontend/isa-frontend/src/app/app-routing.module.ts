import { LoginFormComponent } from './components/login-form/login-form.component';
import { FrontpageComponent } from './components/frontpage/frontpage.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'front-page'},
  {path: 'front-page', component:FrontpageComponent, children: [
    {path: 'login-form', component: LoginFormComponent}
  ]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
