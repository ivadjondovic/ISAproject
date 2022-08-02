import { ReportComponent } from './new-items/report/report.component';
import { NewBoatReservationComponent } from './new-items/new-boat-reservation/new-boat-reservation.component';
import { NewBoatQuickReservationComponent } from './new-items/new-boat-quick-reservation/new-boat-quick-reservation.component';
import { DenyDeletionRequestComponent } from './updates/deny-deletion-request/deny-deletion-request.component';
import { ApproveDeletionRequestComponent } from './updates/approve-deletion-request/approve-deletion-request.component';
import { RequestedDeletionsComponent } from './lists/requested-deletions/requested-deletions.component';
import { RequestForDeletionComponent } from './new-items/request-for-deletion/request-for-deletion.component';
import { NewCottageQuickReservationComponent } from './new-items/new-cottage-quick-reservation/new-cottage-quick-reservation.component';
import { NewCottageReservationComponent } from './new-items/new-cottage-reservation/new-cottage-reservation.component';
import { BoatOwnerHistoryReservationsComponent } from './lists/boat-owner-history-reservations/boat-owner-history-reservations.component';
import { CottageOwnerHistoryReservationsComponent } from './lists/cottage-owner-history-reservations/cottage-owner-history-reservations.component';
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
    {path: 'login/:id/:client', component: LoginFormComponent },
    {path: 'register-service-provider', component: RegisterServiceProviderComponent}
  ]},
  {path: 'home-page', component:HomepageComponent, children: [
    {path: 'lists/owner-cottages/:id', component: OwnCottagesComponent},
    {path: 'lists/owner-boats/:id', component: OwnBoatsComponent},
    {path: 'lists/cottage-owner-history-of-reservations/:id', component: CottageOwnerHistoryReservationsComponent},  
    {path: 'lists/boat-owner-history-of-reservations/:id', component: BoatOwnerHistoryReservationsComponent},
    {path: 'lists/requested-deletions', component: RequestedDeletionsComponent},

    {path: 'updates/update-cottage/:id', component: UpdateCottageComponent},
    {path: 'updates/update-password-cottage-owner', component: UpdatePasswordCottageOwnerComponent}, 
    {path: 'updates/update-cottage-owner/:id', component: UpdateCottageOwnerComponent},
    {path: 'updates/update-boat/:id', component: UpdateBoatComponent},
    {path: 'updates/update-password-boat-owner', component: UpdatePasswordBoatOwnerComponent}, 
    {path: 'updates/update-boat-owner/:id', component: UpdateBoatOwnerComponent},
    {path: 'updates/approve-deletion-request/:id', component: ApproveDeletionRequestComponent},
    {path: 'updates/deny-deletion-request/:id', component: DenyDeletionRequestComponent},

    {path: 'new-items/cottage', component: CottageComponent},
    {path: 'new-items/boat', component: BoatComponent},
    {path: 'new-items/new-cottage-reservation/:id', component: NewCottageReservationComponent},
    {path: 'new-items/new-cottage-quick-reservation/:id', component: NewCottageQuickReservationComponent},
    {path: 'new-items/new-boat-reservation/:id', component: NewBoatReservationComponent},
    {path: 'new-items/new-boat-quick-reservation/:id', component: NewBoatQuickReservationComponent},
    {path: 'new-items/request-for-deletion/:client', component: RequestForDeletionComponent},
    {path: 'new-items/report/:id', component: ReportComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
