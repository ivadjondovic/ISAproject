import { Component, OnInit } from '@angular/core';
import { BoatService } from '../services/boat.service';
import { CottageService } from '../services/cottage.service';
import { FishingLessonService } from '../services/fishing-lesson.service';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  numberOfGuests: string
  numberOfDays: string
  startDate: string;
  entity: string
  cottages: any[]
  boats: any[]
  lessons: any[]
  constructor(public cottageService: CottageService, public boatService: BoatService, public lessonService: FishingLessonService) { }

  ngOnInit(): void {
  }

  find() {
    if (this.entity == 'Cottage') {
      let data = {
        startDate: this.startDate,
        numberOfDays: this.numberOfDays,
        numberOfGuests: this.numberOfGuests
      }
      this.cottageService.getAvailableCottages(data).subscribe((response: any) => {
        this.cottages = response;
        console.log(this.cottages)
      })
    } else if (this.entity == 'Boat') {
      let data = {
        startDate: this.startDate,
        numberOfDays: this.numberOfDays,
        numberOfGuests: this.numberOfGuests
      }
      this.boatService.getAvailableBoats(data).subscribe((response: any) => {
        this.boats = response;
        console.log(this.boats)
      })
    } else if (this.entity == 'Fishing lesson'){
      let data = {
        startDate: this.startDate,
        numberOfDays: this.numberOfDays,
        numberOfGuests: this.numberOfGuests
      }
      this.lessonService.getAvailableLessons(data).subscribe((response: any) => {
        this.lessons = response;
        console.log(this.lessons)
      })
    }
  }

  isCottageListEmpty() {
    if (this.cottages != null) {
      if (this.cottages.length == 0) {
        return true;
      } else {
        return false;
      }
    } else {
      return false
    }
  }

  isBoatListEmpty() {
    if (this.boats != null) {
      if (this.boats.length == 0) {
        return true;
      } else {
        return false;
      }
    } else {
      return false
    }
  }

  isLessonListEmpty() {
    if (this.lessons != null) {
      if (this.lessons.length == 0) {
        return true;
      } else {
        return false;
      }
    } else {
      return false
    }
  }

}
