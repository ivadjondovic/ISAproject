import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FishingLessonRevisionService } from '../services/fishing-lesson-revision.service';
import { UserService } from '../services/user.service';

export interface DialogData {
  id: string;
  type: string
}


@Component({
  selector: 'app-rating-lesson-dialog',
  templateUrl: './rating-lesson-dialog.component.html',
  styleUrls: ['./rating-lesson-dialog.component.css']
})
export class RatingLessonDialogComponent implements OnInit {

  description = ""
  user: any
  entityRate = ""
  ownerRate = ""

  constructor(private _snackBar: MatSnackBar, public dialogRef: MatDialogRef<RatingLessonDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData, public userService: UserService, public revisionService: FishingLessonRevisionService) { }

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
    } else if (this.entityRate == "") {
      this._snackBar.open('Enter boat rate.', 'Close', { duration: 2500 })
    } else if (this.ownerRate == "") {
      this._snackBar.open('Enter boat owner rate.', 'Close', { duration: 2500 })
    } else {

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

}
