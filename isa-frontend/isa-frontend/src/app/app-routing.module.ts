import { CottageComponent } from './new-items/cottage/cottage.component';
import { UpdateCottageComponent } from './updates/update-cottage/update-cottage.component';
import { OwnCottagesComponent } from './lists/own-cottages/own-cottages.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { RegisterServiceProviderComponent } from './components/register-service-provider/register-service-provider.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { FrontpageComponent } from './components/frontpage/frontpage.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'front-page'},
  {path: 'front-page', component:FrontpageComponent, children: [
    {path: 'login-form', component: LoginFormComponent},
    {path: 'register-service-provider', component: RegisterServiceProviderComponent}
  ]},
  {path: 'home-page', component:HomepageComponent, children: [
    {path: 'lists/owner-cottages/:id', component: OwnCottagesComponent},
    {path: 'updates/cottage/:id', component: UpdateCottageComponent},
    {path: 'new-items/cottage', component: CottageComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
