import { NgModule } from '@angular/core';
import { AuthenticationGuard } from './authentication.guard';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { ActivatedAccountComponent } from './activated-account/activated-account.component';
import { ServiceProviderRegistrationComponent } from './service-provider-registration/service-provider-registration.component';
import { RegistrationApprovalComponent } from './registration-approval/registration-approval.component';
import { ClientProfileComponent } from './client-profile/client-profile.component';
import { AdminProfileComponent } from './admin-profile/admin-profile.component';
import { AdminRegistrationComponent } from './admin-registration/admin-registration.component';
import { ChangeAdminPasswordComponent } from './change-admin-password/change-admin-password.component';
import { BoatsComponent } from './boats/boats.component';
import { BoatAdditionalInfoComponent } from './boat-additional-info/boat-additional-info.component';

const routes: Routes = [

  {path: 'homepage', component: HomepageComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'login', component: LoginComponent},
  {path: 'activatedAccount', component: ActivatedAccountComponent},
  {path: 'serviceProviderRegistration', component: ServiceProviderRegistrationComponent},
  {path: 'adminRegistration', component: AdminRegistrationComponent, canActivate: [AuthenticationGuard],  data: {role: 'ROLE_ADMIN'}},
  {path: 'registrationApproval', component: RegistrationApprovalComponent, canActivate: [AuthenticationGuard],  data: {role: 'ROLE_ADMIN'}},
  {path: 'clientProfile', component: ClientProfileComponent, canActivate: [AuthenticationGuard],  data: {role: 'ROLE_CLIENT'}},
  {path: 'adminProfile', component: AdminProfileComponent, canActivate: [AuthenticationGuard],  data: {role: 'ROLE_ADMIN'}},
  {path: 'changeAdminPassword', component: ChangeAdminPasswordComponent, canActivate: [AuthenticationGuard],  data: {role: 'ROLE_ADMIN'}},
  {path: 'boats', component: BoatsComponent},
  { path: 'boatInfo/:id', component: BoatAdditionalInfoComponent }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
