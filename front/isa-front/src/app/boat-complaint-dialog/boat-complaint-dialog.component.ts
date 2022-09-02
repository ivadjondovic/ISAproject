import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { BoatComplaintService } from '../services/boat-complaint.service';
import { UserService } from '../services/user.service';


export interface DialogData {
  id: string;
  type: string
}


@Component({
  selector: 'app-boat-complaint-dialog',
  templateUrl: './boat-complaint-dialog.component.html',
  styleUrls: ['./boat-complaint-dialog.component.css']
})
export class BoatComplaintDialogComponent implements OnInit {

  description = ""
  user: any

  constructor(private _snackBar: MatSnackBar, public dialogRef: MatDialogRef<BoatComplaintDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData, public userService: UserService, public complaintService: BoatComplaintService) { }

  ngOnInit(): void {
    console.log(this.data.id)
    console.log(this.data.type)
    this.userService.current().subscribe((response: any) => {
      this.user = response;

    })
  }

  onNoClick(): void {
    this.dialogRef.close();

  }

  submit() {
    if (this.description == "") {
      this._snackBar.open('Enter description.', 'Close', { duration: 2500 })
    } else {
      let data = {
        reservationId: this.data.id,
        reservationType: this.data.type,
        description: this.description,
        clientId: this.user.id

      }

      this.complaintService.addComplaint(data).subscribe((response: any) => {
        console.log(response)
        this.dialogRef.close();
      })

    }
  }

}
