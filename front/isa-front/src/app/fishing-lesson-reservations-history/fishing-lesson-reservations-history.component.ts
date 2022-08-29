import { Component, OnInit } from '@angular/core';
import { FishingLessonReservationService } from '../services/fishing-lesson-reservation.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-fishing-lesson-reservations-history',
  templateUrl: './fishing-lesson-reservations-history.component.html',
  styleUrls: ['./fishing-lesson-reservations-history.component.css']
})
export class FishingLessonReservationsHistoryComponent implements OnInit {

  reservations: any[]
  reservationList: any[]
  user: any
  constructor(public service: FishingLessonReservationService, public userService: UserService) { }

  ngOnInit(): void {
    this.userService.current().subscribe((response: any) => {
      this.user = response;
      this.service.getByClientId(this.user.id).subscribe((response: any) => {
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
      let id = r.id;
      let fishingLesson = r.fishingLesson;

      let data = {
        id: id,
        fishingLesson: fishingLesson,
        startDate: startDate,
        endDate: endDate,
        price: price,
        additionalServices: additionalServices
      }
      this.reservationList.push(data);
      console.log(startDate)

    }

  }

}
