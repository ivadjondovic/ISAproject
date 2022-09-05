import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ComplaintsService } from '../services/complaints.service';
import { UserService } from '../services/user.service';

export interface DialogData {
  id: string;
  type: string;
  clientId: string;
}

@Component({
  selector: 'app-answer-complaint-dialog',
  templateUrl: './answer-complaint-dialog.component.html',
  styleUrls: ['./answer-complaint-dialog.component.css']
})
export class AnswerComplaintDialogComponent implements OnInit {

  answer = ""


  constructor(private _snackBar: MatSnackBar, public dialogRef: MatDialogRef<AnswerComplaintDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData, public userService: UserService, public complaintsService: ComplaintsService) { }

  ngOnInit(): void {
    console.log(this.data.id)
    console.log(this.data.type)

  }

  onNoClick(): void {
    this.dialogRef.close();

  }

  submit() {
    if (this.answer == "") {
      this._snackBar.open('Enter answer.', 'Close', { duration: 2500 })
    } else {
      let data = {
        complaintId: this.data.id,
        complaintType: this.data.type,
        answer: this.answer,
        clientId: this.data.clientId

      }

      this.complaintsService.answer(data).subscribe((response: any) => {
        console.log(response)
        this.dialogRef.close();
      }, error => {
        this._snackBar.open('Complaint answer failed!', 'Close', {duration: 2500})});

    }
  }

}
