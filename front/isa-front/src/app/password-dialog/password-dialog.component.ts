import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
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

  oldPassword = ""
  newPassword = ""
  confirmPassword = ""
  constructor(private _snackBar: MatSnackBar, public dialogRef: MatDialogRef<PasswordDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData, public service: UserService) { }

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();

  }

  changePassword() {

    if (this.newPassword != this.confirmPassword) {
      this._snackBar.open('Passwords do not matches!.', 'Close', { duration: 2500 })
    }
    else if (this.newPassword == "") {
      this._snackBar.open('Enter your password.', 'Close', { duration: 2500 })
    }
    else if (this.confirmPassword == "") {
      this._snackBar.open('Enter confirm password.', 'Close', { duration: 2500 })
    }
    else {
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

      }, error => {
        this._snackBar.open('Password changed failed!', 'Close', {duration: 3000})});

    }

  }

}
