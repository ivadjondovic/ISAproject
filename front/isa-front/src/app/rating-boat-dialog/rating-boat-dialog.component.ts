import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { BoatRevisionService } from '../services/boat-revision.service';
import { UserService } from '../services/user.service';

export interface DialogData {
  id: string;
  type: string
 }

@Component({
  selector: 'app-rating-boat-dialog',
  templateUrl: './rating-boat-dialog.component.html',
  styleUrls: ['./rating-boat-dialog.component.css']
})
export class RatingBoatDialogComponent implements OnInit {

  description: string
  user: any
  entityRate: any
  ownerRate: any

  constructor(public dialogRef: MatDialogRef<RatingBoatDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData, public userService: UserService, public revisionService: BoatRevisionService) { }

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
    let data = {
      reservationId: this.data.id,
      reservationType: this.data.type,
      description: this.description,
      entityRate: this.entityRate,
      ownerRate: this.ownerRate,
      clientId: this.user.id

    }

    this.revisionService.addRevision(data).subscribe((response: any) => {
      console.log(response)
      this.dialogRef.close();
    })
  }

}
