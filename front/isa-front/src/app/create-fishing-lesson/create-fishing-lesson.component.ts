import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FishingLessonService } from '../services/fishing-lesson.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-create-fishing-lesson',
  templateUrl: './create-fishing-lesson.component.html',
  styleUrls: ['./create-fishing-lesson.component.css']
})
export class CreateFishingLessonComponent implements OnInit {

  name = ""
  address = ""
  description = ""
  numberOfPeople = ""
  instructorBio = ""
  price = ""
  percentageForKeep = ""
  user: any


  constructor(private _snackBar: MatSnackBar, public fishingLessonService: FishingLessonService,
    public userService: UserService) { }

  ngOnInit(): void {
    this.userService.current().subscribe((response: any) => {
      this.user = response
    })
  }

  save() {

    if (this.name == "") {
      this._snackBar.open('Enter lesson name.', 'Close', { duration: 2500 })
    }
    else if (this.description == "") {
      this._snackBar.open('Enter description.', 'Close', { duration: 2500 })
    }
    else if (this.instructorBio == "") {
      this._snackBar.open('Enter fishing instructor biography.', 'Close', { duration: 2500 })
    }
    else if (this.numberOfPeople == "") {
      this._snackBar.open('Enter number of people.', 'Close', { duration: 2500 })
    }
    else if (this.price == "") {
      this._snackBar.open('Enter price.', 'Close', { duration: 2500 })
    }
    else if (this.percentageForKeep == "") {
      this._snackBar.open('Enter country.', 'Close', { duration: 2500 })
    }
    else if (this.address == "") {
      this._snackBar.open('Enter percentage for keep.', 'Close', { duration: 2500 })
    }
    else {

      let data = {
        name: this.name,
        address: this.address,
        description: this.description,
        fishingInstructorBio: this.instructorBio,
        numberOfPeople: this.numberOfPeople,
        price: this.price,
        percentageForKeep: this.percentageForKeep,
        instructorId: this.user.id
      }

      this.fishingLessonService.createFishingLesson(data).subscribe((response: any) => {
        console.log(response);
        location.reload();
      }, error => {
        this._snackBar.open('Fishing lesson creation failed!', 'Close', {duration: 3000})});

    }
  }

}
