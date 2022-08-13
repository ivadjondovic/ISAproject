import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
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

  reason: string
  user: any

  constructor(public dialogRef: MatDialogRef<DialogOverviewComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData, public service: UserService) { }

  
  ngOnInit(): void {
    console.log(this.data.username);
  }

  onNoClick(): void {
    this.dialogRef.close();
    
  }

  disapprove() {
    let data = {
      username : this.data.username,
      reason: this.reason
    }

    this.service.declineActivation(data).subscribe((response: any) => {
      this.user = response;
      console.log(response);
      this.dialogRef.close();
      location.reload();
    })
  }

}
