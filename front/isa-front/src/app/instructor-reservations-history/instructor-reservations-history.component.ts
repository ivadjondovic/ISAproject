import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FishingLessonReservationService } from '../services/fishing-lesson-reservation.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-instructor-reservations-history',
  templateUrl: './instructor-reservations-history.component.html',
  styleUrls: ['./instructor-reservations-history.component.css']
})
export class InstructorReservationsHistoryComponent implements OnInit {

  reservations: any[]
  reservationList: any[]
  user: any
  sortBy = ''
  sortType = ''
  id: any
  client: any
  constructor(public service: FishingLessonReservationService, public userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.userService.current().subscribe((response: any) => {
      this.user = response;
      this.service.getByInstructorId(this.user.id).subscribe((response: any) => {
        this.reservations = response;
        console.log(this.reservations)

        this.corectDate();
      })
    })
    
  }

  corectDate() {

    this.reservationList = [];
    for (let r of this.reservations) {
      let startDate = new Date(r.startDate[0], r.startDate[1] - 1, r.startDate[2], r.startDate[3], r.startDate[4]);
      let endDate = new Date(r.endDate[0], r.endDate[1] - 1, r.endDate[2], r.endDate[3], r.endDate[4]);
      let price = r.price;
      let additionalServices = r.additionalServices;
      let client = r.client;
      let id = r.id;
      let fishingLesson = r.fishingLesson;
      let possibleToRate = r.possibleToRate
      let reservationType = r.reservationType

      let data = {
        id: id,
        fishingLesson: fishingLesson,
        startDate: startDate,
        endDate: endDate,
        price: price,
        additionalServices: additionalServices,
        possibleToRate: possibleToRate,
        reservationType: reservationType,
        client: client
      }
      this.reservationList.push(data);
      console.log(startDate)

    }

  }

  showClient(id: string) {
    this.router.navigate(['/clientInfo', id])

  }
}
