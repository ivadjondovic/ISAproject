import { NullVisitor } from '@angular/compiler/src/render3/r3_ast';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { idText } from 'typescript';
import { BoatReservationService } from '../services/boat-reservation.service';
import { BoatService } from '../services/boat.service';
import { CottageReservationService } from '../services/cottage-reservation.service';
import { CottageService } from '../services/cottage.service';
import { FishingLessonReservationService } from '../services/fishing-lesson-reservation.service';
import { FishingLessonService } from '../services/fishing-lesson.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {

  numberOfGuests = ''
  numberOfDays = ''
  startDate = ''
  entity = ''
  cottages: any[]
  boats: any[]
  lessons: any[]
  sortCottageBy = '';
  sortCottageType = '';
  sortBoatBy = '';
  sortBoatType = '';
  sortLessonBy = '';
  sortLessonType = '';
  cottage: any = {} as any
  boat: any = {} as any
  lesson: any = {} as any
  user: any
  additionalCottageServices = new Set<number>();
  additionalBoatServices = new Set<number>();
  additionalLessonServices = new Set<number>();
  constructor(private _snackBar: MatSnackBar, public lessonReservationService: FishingLessonReservationService, public boatReservationService: BoatReservationService, public cottageReservationService: CottageReservationService, public userService: UserService, public cottageService: CottageService, public boatService: BoatService, public lessonService: FishingLessonService) { }

  ngOnInit(): void {
    this.cottage = null
    this.boat = null
    this.lesson = null
    this.userService.current().subscribe((response: any) => {
      this.user = response;
    })

  }

  find() {
    let date = new Date(this.startDate)
    if (date.getTime() < Date.now()) {
      this._snackBar.open('You have selected a date that has passed!', 'Close', { duration: 2500 })
    }
    else if (this.entity == '') {
      this._snackBar.open('Select entity for reservation!', 'Close', { duration: 2500 })
    }
    else if (this.startDate == '') {
      this._snackBar.open('Enter start date!', 'Close', { duration: 2500 })
    }
    else if (this.numberOfDays == '') {
      this._snackBar.open('Enter number of days!', 'Close', { duration: 2500 })
    }
    else if (this.numberOfGuests == '') {
      this._snackBar.open('Enter number of guests!', 'Close', { duration: 2500 })
    }
    else {

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
          this.boat = null
          this.lesson = null
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
          this.cottage = null
          this.lesson = null
        })
      } else if (this.entity == 'Fishing lesson') {
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
          this.cottage = null
          this.boat = null
        })
      }

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

  sortCottage() {
    console.log(this.sortCottageBy)
    let sortingBy = this.sortCottageBy
    let sortingType = this.sortCottageType
    console.log(this.sortCottageType)
    if (this.sortCottageBy == '') {
      this._snackBar.open('Enter sort by.', 'Close', { duration: 2500 })
    } else if (this.sortCottageType == '') {
      this._snackBar.open('Enter sort type.', 'Close', { duration: 2500 })
    }
    else if (this.startDate == '') {
      this._snackBar.open('Enter start date!', 'Close', { duration: 2500 })
    }
    else if (this.numberOfDays == '') {
      this._snackBar.open('Enter number of days!', 'Close', { duration: 2500 })
    }
    else if (this.numberOfGuests == '') {
      this._snackBar.open('Enter number of guests!', 'Close', { duration: 2500 })
    }
    else {
      console.log('OK')
      let data = {
        startDate: this.startDate,
        numberOfDays: this.numberOfDays,
        numberOfGuests: this.numberOfGuests,
        sortBy: sortingBy,
        sortType: sortingType
      }
      console.log(data)

      this.cottageService.sortAvailable(data).subscribe((response: any) => {
        this.cottages = response;
        console.log(this.cottages)
      })
    }
  }

  sortBoat() {
    console.log(this.sortBoatBy)
    let sortingBy = this.sortBoatBy
    let sortingType = this.sortBoatType
    console.log(this.sortBoatType)
    if (this.sortBoatBy == '') {
      this._snackBar.open('Enter sort by.', 'Close', { duration: 2500 })
    } else if (this.sortBoatType == '') {
      this._snackBar.open('Enter sort type.', 'Close', { duration: 2500 })
    }
    else if (this.startDate == '') {
      this._snackBar.open('Enter start date!', 'Close', { duration: 2500 })
    }
    else if (this.numberOfDays == '') {
      this._snackBar.open('Enter number of days!', 'Close', { duration: 2500 })
    }
    else if (this.numberOfGuests == '') {
      this._snackBar.open('Enter number of guests!', 'Close', { duration: 2500 })
    }
    else {
      console.log('OK')
      let data = {
        startDate: this.startDate,
        numberOfDays: this.numberOfDays,
        numberOfGuests: this.numberOfGuests,
        sortBy: sortingBy,
        sortType: sortingType
      }
      console.log(data)

      this.boatService.sortAvailable(data).subscribe((response: any) => {
        this.boats = response;
        console.log(this.boats)
      })
    }
  }

  sortLesson() {
    console.log(this.sortLessonBy)
    let sortingBy = this.sortLessonBy
    let sortingType = this.sortLessonType
    console.log(this.sortLessonType)
    if (this.sortLessonBy == '') {
      this._snackBar.open('Enter sort by.', 'Close', { duration: 2500 })
    } else if (this.sortLessonType == '') {
      this._snackBar.open('Enter sort type.', 'Close', { duration: 2500 })
    }
    else if (this.startDate == '') {
      this._snackBar.open('Enter start date!', 'Close', { duration: 2500 })
    }
    else if (this.numberOfDays == '') {
      this._snackBar.open('Enter number of days!', 'Close', { duration: 2500 })
    }
    else if (this.numberOfGuests == '') {
      this._snackBar.open('Enter number of guests!', 'Close', { duration: 2500 })
    }
    else {
      console.log('OK')
      let data = {
        startDate: this.startDate,
        numberOfDays: this.numberOfDays,
        numberOfGuests: this.numberOfGuests,
        sortBy: sortingBy,
        sortType: sortingType
      }
      console.log(data)

      this.lessonService.sortAvailable(data).subscribe((response: any) => {
        this.lessons = response;
        console.log(this.lessons)
      })
    }
  }

  bookCottage(id: string) {
    this.cottageService.getById(id).subscribe((response: any) => {
      this.cottage = response;
      console.log(this.cottage)
    })
  }

  bookBoat(id: string) {
    this.boatService.getById(id).subscribe((response: any) => {
      this.boat = response;
      console.log(this.boat)
    })
  }

  bookLesson(id: string) {
    this.lessonService.getOneLesson(id).subscribe((response: any) => {
      this.lesson = response;
      console.log(this.lesson)
    })
  }

  addCottageService(id: string) {
    this.additionalCottageServices.add(+id);
    console.log(this.additionalCottageServices)

  }

  deleteCottageService(id: string) {
    this.additionalCottageServices.delete(+id)
    console.log(this.additionalCottageServices)

  }

  isCottageServiceAdded(id: string) {
    return this.additionalCottageServices.has(+id)
  }


  addBoatService(id: string) {
    this.additionalBoatServices.add(+id);
    console.log(this.additionalBoatServices)

  }

  deleteBoatService(id: string) {
    this.additionalBoatServices.delete(+id)
    console.log(this.additionalBoatServices)

  }

  isBoatServiceAdded(id: string) {
    return this.additionalBoatServices.has(+id)
  }


  addLessonService(id: string) {
    this.additionalLessonServices.add(+id);
    console.log(this.additionalLessonServices)

  }

  deleteLessonService(id: string) {
    this.additionalLessonServices.delete(+id)
    console.log(this.additionalLessonServices)

  }

  isLessonServiceAdded(id: string) {
    return this.additionalLessonServices.has(+id)
  }

  reservation() {

    let additionalServicesForCottage = Array.from(this.additionalCottageServices);

    if (this.startDate == '') {
      this._snackBar.open('Enter start date!', 'Close', { duration: 2500 })
    }
    else if (this.numberOfDays == '') {
      this._snackBar.open('Enter number of days!', 'Close', { duration: 2500 })
    } else {

      if (this.entity == 'Cottage') {
        let data = {
          startDate: this.startDate,
          numberOfDays: this.numberOfDays,
          clientId: this.user.id,
          entityId: this.cottage.id,
          additionalServices: additionalServicesForCottage

        }
        this.cottageReservationService.createReservation(data).subscribe((response: any) => {
          console.log(response)
          location.reload();
        }, error => {
          this._snackBar.open('Reservation failed! Chose another date and try again!', 'Close', {duration: 4000})});

      }

      let additionalServicesForBoat = Array.from(this.additionalBoatServices);
      if (this.entity == 'Boat') {
        let data = {
          startDate: this.startDate,
          numberOfDays: this.numberOfDays,
          clientId: this.user.id,
          entityId: this.boat.id,
          additionalServices: additionalServicesForBoat

        }
        this.boatReservationService.createReservation(data).subscribe((response: any) => {
          console.log(response)
          location.reload();
        }, error => {
          this._snackBar.open('Reservation failed! Chose another date and try again!', 'Close', {duration: 4000})});
        

      }
      let additionalServicesForLesson = Array.from(this.additionalLessonServices);
      if (this.entity == 'Fishing lesson') {
        let data = {
          startDate: this.startDate,
          numberOfDays: this.numberOfDays,
          clientId: this.user.id,
          entityId: this.lesson.id,
          additionalServices: additionalServicesForLesson

        }
        this.lessonReservationService.createReservation(data).subscribe((response: any) => {
          console.log(response)
          location.reload();
        }
        , error => {
          this._snackBar.open('Reservation failed! Choose another date and try again!', 'Close', {duration: 4000})});

      }

    }
  }




}
