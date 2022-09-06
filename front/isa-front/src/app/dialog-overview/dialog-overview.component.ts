import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from '../services/user.service';


export interface DialogData {
  username: string;
}

@Component({
  selector: 'app-dialog-overview',
  templateUrl: './dialog-overview.component.html',
  styleUrls: ['./dialog-overview.component.css']
})
export class DialogOverviewComponent implements OnInit {

  reason = ""
  user: any

  constructor(private _snackBar: MatSnackBar, public dialogRef: MatDialogRef<DialogOverviewComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData, public service: UserService) { }


  ngOnInit(): void {
    console.log(this.data.username);
  }

  onNoClick(): void {
    this.dialogRef.close();

  }

  disapprove() {

    if (this.reason == "") {
      this._snackBar.open('Enter reason!.', 'Close', { duration: 2500 })
    } else {
      let data = {
        username: this.data.username,
        reason: this.reason
      }

      this.service.declineActivation(data).subscribe((response: any) => {
        this.user = response;
        console.log(response);
        this.dialogRef.close();
        location.reload();
      }, error => {
        this._snackBar.open('Account disapproval failed!', 'Close', {duration: 3000})});

    }
  }

}
