import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FishingLessonReservationService } from '../services/fishing-lesson-reservation.service';
import { FishingLessonService } from '../services/fishing-lesson.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-instructor-reservation',
  templateUrl: './instructor-reservation.component.html',
  styleUrls: ['./instructor-reservation.component.css']
})
export class InstructorReservationComponent implements OnInit {

  numberOfGuests = ''
  numberOfDays = ''
  startDate = ''
  lessons: any[]
  lesson: any = {} as any
  user: any
  additionalLessonServices = new Set<number>();
  clients: any[]
  selectedClient: any

  constructor(private _snackBar: MatSnackBar, public lessonReservationService: FishingLessonReservationService, public userService: UserService, public lessonService: FishingLessonService) { }

  ngOnInit(): void {
    this.lesson = null
    this.userService.current().subscribe((response: any) => {
      this.user = response;
      this.userService.getClients().subscribe((response: any) => {
        this.clients = response;
        console.log(this.clients)
      })
    })

  }

  find() {
    let date = new Date(this.startDate)
    if (date.getTime() < Date.now()) {
      this._snackBar.open('You have selected a date that has passed!', 'Close', { duration: 2500 })
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

        let data = {
          startDate: this.startDate,
          numberOfDays: this.numberOfDays,
          numberOfGuests: this.numberOfGuests,
          instructorId: this.user.id
        }
        this.lessonService.getAvailableLessonsForInstructor(data).subscribe((response: any) => {
          this.lessons = response;
          console.log(this.lessons)
        })

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

  bookLesson(id: string) {
    this.lessonService.getOneLesson(id).subscribe((response: any) => {
      this.lesson = response;
      console.log(this.lesson)
    })
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

    if (this.startDate == '') {
      this._snackBar.open('Enter start date!', 'Close', { duration: 2500 })
    }
    else if (this.numberOfDays == '') {
      this._snackBar.open('Enter number of days!', 'Close', { duration: 2500 })
    } 
    else if (this.selectedClient == null) {
      this._snackBar.open('Select a client!', 'Close', { duration: 2500 })
    } else {

      
      let additionalServicesForLesson = Array.from(this.additionalLessonServices);
        let data = {
          startDate: this.startDate,
          numberOfDays: this.numberOfDays,
          clientId: this.selectedClient.id,
          entityId: this.lesson.id,
          additionalServices: additionalServicesForLesson

        }
        this.lessonReservationService.createReservation(data).subscribe((response: any) => {
          console.log(response)
          location.reload();
        })


    }
  }


}
