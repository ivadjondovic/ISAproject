import { BoatComponent } from './new-items/boat/boat.component';
import { UpdateBoatOwnerComponent } from './updates/update-boat-owner/update-boat-owner.component';
import { UpdatePasswordBoatOwnerComponent } from './updates/update-password-boat-owner/update-password-boat-owner.component';
import { UpdateBoatComponent } from './updates/update-boat/update-boat.component';
import { OwnBoatsComponent } from './lists/own-boats/own-boats.component';
import { UpdateCottageOwnerComponent } from './updates/update-cottage-owner/update-cottage-owner.component';
import { UpdatePasswordCottageOwnerComponent } from './updates/update-password-cottage-owner/update-password-cottage-owner.component';
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
    {path: 'lists/owner-boats/:id', component: OwnBoatsComponent},

    {path: 'updates/update-cottage/:id', component: UpdateCottageComponent},
    {path: 'updates/update-password-cottage-owner', component: UpdatePasswordCottageOwnerComponent}, 
    {path: 'updates/update-cottage-owner/:id', component: UpdateCottageOwnerComponent},
    {path: 'updates/update-boat/:id', component: UpdateBoatComponent},
    {path: 'updates/update-password-boat-owner', component: UpdatePasswordBoatOwnerComponent}, 
    {path: 'updates/update-boat-owner/:id', component: UpdateBoatOwnerComponent},

    {path: 'new-items/cottage', component: CottageComponent},
    {path: 'new-items/boat', component: BoatComponent}

  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
