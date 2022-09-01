import { Component, OnInit } from '@angular/core';
import { FishingLessonService } from '../services/fishing-lesson.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-create-fishing-lesson',
  templateUrl: './create-fishing-lesson.component.html',
  styleUrls: ['./create-fishing-lesson.component.css']
})
export class CreateFishingLessonComponent implements OnInit {

  name: any
  address: any
  description: any
  numberOfPeople: any
  instructorBio: any
  price: any
  percentageForKeep: any
  user: any


  constructor(public fishingLessonService: FishingLessonService,
              public userService: UserService) { }

  ngOnInit(): void {
    this.userService.current().subscribe((response: any) => {
      this.user = response
    })
  }

  save() {
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
    })
  }

}
