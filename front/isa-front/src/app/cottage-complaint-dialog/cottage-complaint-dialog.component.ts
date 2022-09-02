import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CottageComplaintService } from '../services/cottage-complaint.service';
import { UserService } from '../services/user.service';


export interface DialogData {
  id: string;
  type: string
}

@Component({
  selector: 'app-cottage-complaint-dialog',
  templateUrl: './cottage-complaint-dialog.component.html',
  styleUrls: ['./cottage-complaint-dialog.component.css']
})
export class CottageComplaintDialogComponent implements OnInit {

  description = ""
  user: any

  constructor(private _snackBar: MatSnackBar, public dialogRef: MatDialogRef<CottageComplaintDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData, public userService: UserService, public complaintService: CottageComplaintService) { }

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
