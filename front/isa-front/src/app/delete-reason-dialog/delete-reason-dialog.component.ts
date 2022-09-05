import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from '../services/user.service';


export interface DialogData {
  id: string;
}

@Component({
  selector: 'app-delete-reason-dialog',
  templateUrl: './delete-reason-dialog.component.html',
  styleUrls: ['./delete-reason-dialog.component.css']
})
export class DeleteReasonDialogComponent implements OnInit {

  reason = ""
  user: any

  constructor(private _snackBar: MatSnackBar, public dialogRef: MatDialogRef<DeleteReasonDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData, public service: UserService) { }


  ngOnInit(): void {
    console.log(this.data.id);
  }

  onNoClick(): void {
    this.dialogRef.close();

  }

  accept() {
    if (this.reason == "") {
      this._snackBar.open('Enter deletion request.', 'Close', { duration: 2500 })
    } else {
      let data = {
        reason: this.reason,
        userId: this.data.id
      }

      this.service.acceptDeactivation(data).subscribe((response: any) => {
        this.user = response;
        console.log(response);
        this.dialogRef.close();
        location.reload();
      }, error => {
        this._snackBar.open('Unable to accept! Deletion request has already been reviewed!', 'Close', {duration: 2500})});

    }
  }

}
