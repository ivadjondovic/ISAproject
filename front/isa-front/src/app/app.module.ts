import { NgModule,  CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomepageComponent } from './homepage/homepage.component';
import { HeaderComponent } from './header/header.component';
import { SidenavComponent } from './sidenav/sidenav.component';

import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MatGridListModule} from '@angular/material/grid-list';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatDialogModule } from '@angular/material/dialog';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatCardModule} from '@angular/material/card';
import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';
import { AuthenticationGuard } from './authentication.guard';
import { ActivatedAccountComponent } from './activated-account/activated-account.component';
import { ServiceProviderRegistrationComponent } from './service-provider-registration/service-provider-registration.component';
import { RegistrationApprovalComponent } from './registration-approval/registration-approval.component';
import { DialogOverviewComponent } from './dialog-overview/dialog-overview.component';
import { ClientProfileComponent } from './client-profile/client-profile.component';
import { PasswordDialogComponent } from './password-dialog/password-dialog.component';
import { AdminProfileComponent } from './admin-profile/admin-profile.component';
import { AdminRegistrationComponent } from './admin-registration/admin-registration.component';
import { ChangeAdminPasswordComponent } from './change-admin-password/change-admin-password.component';
import { BoatsComponent } from './boats/boats.component';
import { BoatAdditionalInfoComponent } from './boat-additional-info/boat-additional-info.component';
import { CottagesComponent } from './cottages/cottages.component';
import { CottageAdditionalInfoComponent } from './cottage-additional-info/cottage-additional-info.component';
import { MatInputModule } from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import { DeleteAccountComponent } from './delete-account/delete-account.component';
import { DeleteRequestsComponent } from './delete-requests/delete-requests.component';
import { DeleteReasonDialogComponent } from './delete-reason-dialog/delete-reason-dialog.component';
import { NotDeleteReasonDialogComponent } from './not-delete-reason-dialog/not-delete-reason-dialog.component';
import { FishingLessonsComponent } from './fishing-lessons/fishing-lessons.component';
import { EditFishingLessonComponent } from './edit-fishing-lesson/edit-fishing-lesson.component';
import { ClientFishingLessonsComponent } from './client-fishing-lessons/client-fishing-lessons.component';
import { FishingLessonAdditionalInfoComponent } from './fishing-lesson-additional-info/fishing-lesson-additional-info.component';
import { ReservationComponent } from './reservation/reservation.component';
import { CottageReservationsHistoryComponent } from './cottage-reservations-history/cottage-reservations-history.component';
import { BoatReservationsHistoryComponent } from './boat-reservations-history/boat-reservations-history.component';
import { FishingLessonReservationsHistoryComponent } from './fishing-lesson-reservations-history/fishing-lesson-reservations-history.component';
import { NotPassedReservationsComponent } from './not-passed-reservations/not-passed-reservations.component';
import { RatingCottageDialogComponent } from './rating-cottage-dialog/rating-cottage-dialog.component';
import { RatingBoatDialogComponent } from './rating-boat-dialog/rating-boat-dialog.component';
import { RatingLessonDialogComponent } from './rating-lesson-dialog/rating-lesson-dialog.component';
import { AdminRevisionsComponent } from './admin-revisions/admin-revisions.component';
import { BoatComplaintDialogComponent } from './boat-complaint-dialog/boat-complaint-dialog.component';
import { CottageComplaintDialogComponent } from './cottage-complaint-dialog/cottage-complaint-dialog.component';
import { FishingLessonComplaintDialogComponent } from './fishing-lesson-complaint-dialog/fishing-lesson-complaint-dialog.component';
import { AdminComplaintsComponent } from './admin-complaints/admin-complaints.component';
import { AnswerComplaintDialogComponent } from './answer-complaint-dialog/answer-complaint-dialog.component';
import { CreateFishingLessonComponent } from './create-fishing-lesson/create-fishing-lesson.component';
import { ClientSubscriptionsComponent } from './client-subscriptions/client-subscriptions.component';
import { MatSnackBarModule} from '@angular/material/snack-bar';
import { InstructorProfileComponent } from './instructor-profile/instructor-profile.component';
import { InstructorReservationsHistoryComponent } from './instructor-reservations-history/instructor-reservations-history.component';
import { ClientInfoComponent } from './client-info/client-info.component';
import { InstructorReservationComponent } from './instructor-reservation/instructor-reservation.component';
import { AdminUsersComponent } from './admin-users/admin-users.component';
import { ReviewClientComponent } from './review-client/review-client.component';
import {MatRadioModule} from '@angular/material/radio';
import { AdminPenaltiesComponent } from './admin-penalties/admin-penalties.component';
import { LoyaltyProgramComponent } from './loyalty-program/loyalty-program.component';




@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    HeaderComponent,
    SidenavComponent,
    RegistrationComponent,
    LoginComponent,
    ActivatedAccountComponent,
    ServiceProviderRegistrationComponent,
    RegistrationApprovalComponent,
    DialogOverviewComponent,
    ClientProfileComponent,
    PasswordDialogComponent,
    AdminProfileComponent,
    AdminRegistrationComponent,
    ChangeAdminPasswordComponent,
    BoatsComponent,
    BoatAdditionalInfoComponent,
    CottagesComponent,
    CottageAdditionalInfoComponent,
    DeleteAccountComponent,
    DeleteRequestsComponent,
    DeleteReasonDialogComponent,
    NotDeleteReasonDialogComponent,
    FishingLessonsComponent,
    EditFishingLessonComponent,
    ClientFishingLessonsComponent,
    FishingLessonAdditionalInfoComponent,
    ReservationComponent,
    CottageReservationsHistoryComponent,
    BoatReservationsHistoryComponent,
    FishingLessonReservationsHistoryComponent,
    NotPassedReservationsComponent,
    RatingCottageDialogComponent,
    RatingBoatDialogComponent,
    RatingLessonDialogComponent,
    AdminRevisionsComponent,
    BoatComplaintDialogComponent,
    CottageComplaintDialogComponent,
    FishingLessonComplaintDialogComponent,
    AdminComplaintsComponent,
    AnswerComplaintDialogComponent,
    CreateFishingLessonComponent,
    ClientSubscriptionsComponent,
    InstructorProfileComponent,
    InstructorReservationsHistoryComponent,
    ClientInfoComponent,
    InstructorReservationComponent,
    AdminUsersComponent,
    ReviewClientComponent,
    AdminPenaltiesComponent,
    LoyaltyProgramComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSidenavModule,
    MatIconModule,
    MatToolbarModule,
    MatButtonModule,
    MatListModule,
    MatMenuModule,
    MatGridListModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    FormsModule,
    MatCardModule,
    HttpClientModule,
    MatDialogModule,
    MatInputModule,
    MatSelectModule,
    MatSnackBarModule,
    MatRadioModule
    
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [AuthenticationGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
