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
    UpdateBoatOwnerComponent
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
