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
  sortCottageBy = '';
  sortCottageType = '';
  sortBoatBy = '';
  sortBoatType = '';
  sortLessonBy = '';
  sortLessonType = '';
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
        this.boats = []
        this.lessons = []
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
        this.cottages = [];
        this.lessons = [];
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
        this.cottages = [];
        this.boats = [];
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

  sortCottage(){
    console.log(this.sortCottageBy)
    let sortingBy = this.sortCottageBy
    let sortingType = this.sortCottageType
    console.log(this.sortCottageType)
    if(this.sortCottageBy = ''){
      alert('Choose sort by');
    }else if (this.sortCottageType = ''){
      alert('Choose sort type');
    }else{
      console.log('OK')
      let data = {
        sortBy: sortingBy,
        sortType: sortingType
      }
      console.log(data)

      this.cottageService.sort(data).subscribe((response: any) => {
        this.cottages = response;
        console.log(this.cottages)
      })
    }
  }

  sortBoat(){
    console.log(this.sortBoatBy)
    let sortingBy = this.sortBoatBy
    let sortingType = this.sortBoatType
    console.log(this.sortBoatType)
    if(this.sortBoatBy = ''){
      alert('Choose sort by');
    }else if (this.sortBoatType = ''){
      alert('Choose sort type');
    }else{
      console.log('OK')
      let data = {
        sortBy: sortingBy,
        sortType: sortingType
      }
      console.log(data)

      this.boatService.sort(data).subscribe((response: any) => {
        this.boats = response;
        console.log(this.boats)
      })
    }
  }

  sortLesson(){
    console.log(this.sortLessonBy)
    let sortingBy = this.sortLessonBy
    let sortingType = this.sortLessonType
    console.log(this.sortLessonType)
    if(this.sortLessonBy = ''){
      alert('Choose sort by');
    }else if (this.sortLessonType = ''){
      alert('Choose sort type');
    }else{
      console.log('OK')
      let data = {
        sortBy: sortingBy,
        sortType: sortingType
      }
      console.log(data)

      this.lessonService.sort(data).subscribe((response: any) => {
        this.lessons = response;
        console.log(this.lessons)
      })
    }
  }


}
