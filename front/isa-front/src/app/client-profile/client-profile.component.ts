import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PasswordDialogComponent } from '../password-dialog/password-dialog.component';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-client-profile',
  templateUrl: './client-profile.component.html',
  styleUrls: ['./client-profile.component.css']
})
export class ClientProfileComponent implements OnInit {

  isChangePasswordClicked = false;
  user: any
  country: string
  oldPassword: string
  password: string
  newPassword = ""
  username: string
  constructor(public service: UserService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.service.current().subscribe((response: any) => {
      this.user = response;
      this.password = this.user.password;
    })
  }

  openDialog(enterAnimationDuration: string, exitAnimationDuration: string, username: string): void {
    this.username = username;
    const dialogRef = this.dialog.open(PasswordDialogComponent, {
      width: '40%',
      data: {username: this.username}
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.username = result;
    });
  }
  

  changePasswordClick(){
    this.isChangePasswordClicked =  true;
  }

  editClient(){
    console.log(this.password)
    console.log(this.user.country)
    console.log(this.newPassword)
    let data = {
      username: this.user.username,
      password: this.newPassword,
      name: this.user.name,
      surname: this.user.surname,
      address: this.user.address,
      city: this.user.city,
      country: this.user.country,
      phoneNumber: this.user.phoneNumber
    }
    this.service.editClient(data).subscribe((response: any) => {
      console.log(response);
      location.reload();

    })
    
  }


}
