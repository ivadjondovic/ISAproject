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
import { CottagesComponent } from './cottages/cottages.component';
import { CottageAdditionalInfoComponent } from './cottage-additional-info/cottage-additional-info.component';
import { DeleteAccountComponent } from './delete-account/delete-account.component';
import { DeleteRequestsComponent } from './delete-requests/delete-requests.component';
import { FishingLessonsComponent } from './fishing-lessons/fishing-lessons.component';
import { EditFishingLessonComponent } from './edit-fishing-lesson/edit-fishing-lesson.component';
import { ClientFishingLessonsComponent } from './client-fishing-lessons/client-fishing-lessons.component';
import { FishingLessonAdditionalInfoComponent } from './fishing-lesson-additional-info/fishing-lesson-additional-info.component';
import { ReservationComponent } from './reservation/reservation.component';
import { CottageReservationsHistoryComponent } from './cottage-reservations-history/cottage-reservations-history.component';
import { BoatReservationsHistoryComponent } from './boat-reservations-history/boat-reservations-history.component';
import { NotPassedReservationsComponent } from './not-passed-reservations/not-passed-reservations.component';
import { FishingLessonReservationsHistoryComponent } from './fishing-lesson-reservations-history/fishing-lesson-reservations-history.component';
import { AdminRevisionsComponent } from './admin-revisions/admin-revisions.component';
import { AdminComplaintsComponent } from './admin-complaints/admin-complaints.component';

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
  {path: 'boatInfo/:id', component: BoatAdditionalInfoComponent},
  {path: 'cottages', component: CottagesComponent},
  {path: 'cottageInfo/:id', component: CottageAdditionalInfoComponent},
  {path: 'deleteAccount', component: DeleteAccountComponent, canActivate: [AuthenticationGuard],  data: {role: 'ROLE_CLIENT'}},
  {path: 'deleteRequests', component: DeleteRequestsComponent, canActivate: [AuthenticationGuard],  data: {role: 'ROLE_ADMIN'}},
  {path: 'fishingLessons', component: FishingLessonsComponent, canActivate: [AuthenticationGuard],  data: {role: 'ROLE_INSTRUCTOR'}},
  {path: 'editFishingLesson/:id', component: EditFishingLessonComponent, canActivate: [AuthenticationGuard],  data: {role: 'ROLE_INSTRUCTOR'}},
  {path: 'lessons', component: ClientFishingLessonsComponent},
  {path: 'lessonInfo/:id', component: FishingLessonAdditionalInfoComponent},
  {path: 'reservation', component: ReservationComponent, canActivate: [AuthenticationGuard],  data: {role: 'ROLE_CLIENT'}},
  {path: 'cottageReservationsHistory', component: CottageReservationsHistoryComponent, canActivate: [AuthenticationGuard],  data: {role: 'ROLE_CLIENT'}},
  {path: 'boatReservationsHistory', component: BoatReservationsHistoryComponent, canActivate: [AuthenticationGuard],  data: {role: 'ROLE_CLIENT'}},
  {path: 'fishingLessonReservationsHistory', component: FishingLessonReservationsHistoryComponent, canActivate: [AuthenticationGuard],  data: {role: 'ROLE_CLIENT'}},
  {path: 'notPassedReservations', component: NotPassedReservationsComponent, canActivate: [AuthenticationGuard],  data: {role: 'ROLE_CLIENT'}},
  {path: 'revisions', component: AdminRevisionsComponent, canActivate: [AuthenticationGuard],  data: {role: 'ROLE_ADMIN'}},
  {path: 'complaints', component: AdminComplaintsComponent, canActivate: [AuthenticationGuard],  data: {role: 'ROLE_ADMIN'}},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
