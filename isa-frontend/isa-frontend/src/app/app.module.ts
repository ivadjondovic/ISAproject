import { ReportComponent } from './new-items/report/report.component';
import { NewBoatQuickReservationComponent } from './new-items/new-boat-quick-reservation/new-boat-quick-reservation.component';
import { NewBoatReservationComponent } from './new-items/new-boat-reservation/new-boat-reservation.component';
import { DenyDeletionRequestComponent } from './updates/deny-deletion-request/deny-deletion-request.component';
import { ApproveDeletionRequestComponent } from './updates/approve-deletion-request/approve-deletion-request.component';
import { RequestedDeletionsComponent } from './lists/requested-deletions/requested-deletions.component';
import { RequestForDeletionComponent } from './new-items/request-for-deletion/request-for-deletion.component';
import { NewCottageQuickReservationComponent } from './new-items/new-cottage-quick-reservation/new-cottage-quick-reservation.component';
import { NewCottageReservationComponent } from './new-items/new-cottage-reservation/new-cottage-reservation.component';
import { BoatOwnerHistoryReservationsComponent } from './lists/boat-owner-history-reservations/boat-owner-history-reservations.component';
import { CottageOwnerHistoryReservationsComponent } from './lists/cottage-owner-history-reservations/cottage-owner-history-reservations.component';
import { UpdateBoatOwnerComponent } from './updates/update-boat-owner/update-boat-owner.component';
import { UpdateBoatComponent } from './updates/update-boat/update-boat.component';
import { UpdatePasswordBoatOwnerComponent } from './updates/update-password-boat-owner/update-password-boat-owner.component';
import { OwnBoatsComponent } from './lists/own-boats/own-boats.component';
import { BoatComponent } from './new-items/boat/boat.component';
import { UpdateCottageOwnerComponent } from './updates/update-cottage-owner/update-cottage-owner.component';
import { UpdatePasswordCottageOwnerComponent } from './updates/update-password-cottage-owner/update-password-cottage-owner.component';
import { CottageComponent } from './new-items/cottage/cottage.component';
import { UpdateCottageComponent } from './updates/update-cottage/update-cottage.component';
import { OwnCottagesComponent } from './lists/own-cottages/own-cottages.component';
import { RegisterServiceProviderComponent } from './components/register-service-provider/register-service-provider.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { FrontpageComponent } from './components/frontpage/frontpage.component';
import { DemoNgZorroAntdModule } from './ng-zorro-antd.module';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { en_US } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

registerLocaleData(en);

@NgModule({
  declarations: [
    AppComponent,
    FrontpageComponent,
    LoginFormComponent,
    HomepageComponent,
    RegisterServiceProviderComponent,
    OwnCottagesComponent,
    UpdateCottageComponent,
    CottageComponent,
    UpdatePasswordCottageOwnerComponent,
    UpdateCottageOwnerComponent,
    BoatComponent,
    OwnBoatsComponent,
    UpdatePasswordBoatOwnerComponent,
    UpdateBoatComponent,
    UpdateBoatOwnerComponent,
    CottageOwnerHistoryReservationsComponent,
    BoatOwnerHistoryReservationsComponent,
    NewCottageReservationComponent,
    NewCottageQuickReservationComponent,
    RequestForDeletionComponent,
    RequestedDeletionsComponent,
    ApproveDeletionRequestComponent,
    DenyDeletionRequestComponent,
    NewBoatReservationComponent,
    NewBoatQuickReservationComponent,
    ReportComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    DemoNgZorroAntdModule,
    ReactiveFormsModule
  ],
  providers: [{ provide: NZ_I18N, useValue: en_US }],
  bootstrap: [AppComponent]
})
export class AppModule { }
