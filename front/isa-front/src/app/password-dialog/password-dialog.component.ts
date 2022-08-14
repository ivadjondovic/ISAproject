import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

export interface DialogData {
  username: string;
 }

@Component({
  selector: 'app-password-dialog',
  templateUrl: './password-dialog.component.html',
  styleUrls: ['./password-dialog.component.css']
})
export class PasswordDialogComponent implements OnInit {

  oldPassword: string;
  newPassword: string;
  confirmPassword: string;
  constructor(public dialogRef: MatDialogRef<PasswordDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData, public service: UserService) { }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
    
  }

  changePassword(){
    
    if(this.newPassword != this.confirmPassword){
      alert("Passwords do not matches!")
    }
    console.log(this.data.username)

    let data = {
      oldPassword: this.oldPassword,
      newPassword: this.newPassword
    }
    this.service.changePassword(data).subscribe((response: any) => {
      console.log(response);
      this.dialogRef.close();
      localStorage.clear();
      location.reload();
      
    })
    
  }

}
