import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UserService } from '../services/user.service';


export interface DialogData {
  id: string;
 }


@Component({
  selector: 'app-not-delete-reason-dialog',
  templateUrl: './not-delete-reason-dialog.component.html',
  styleUrls: ['./not-delete-reason-dialog.component.css']
})
export class NotDeleteReasonDialogComponent implements OnInit {

  reason: string
  user: any

  constructor(public dialogRef: MatDialogRef<NotDeleteReasonDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData, public service: UserService) { }

  
  ngOnInit(): void {
    console.log(this.data.id);
  }

  onNoClick(): void {
    this.dialogRef.close();
    
  }

  decline() {
    let data = {
      reason: this.reason,
      userId: this.data.id
    }

    this.service.declineDeactivation(data).subscribe((response: any) => {
      this.user = response;
      console.log(response);
      this.dialogRef.close();
      location.reload();
    })
  }

}
