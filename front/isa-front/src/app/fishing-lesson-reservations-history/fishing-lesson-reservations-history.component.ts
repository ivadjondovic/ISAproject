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
  sortBy: string
  sortType: string
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
        reservationType: reservationType
      }
      this.reservationList.push(data);
      console.log(startDate)

    }

  }

  sort(){
    console.log(this.sortBy)
    let sortingBy = this.sortBy
    let sortingType = this.sortType
    console.log(this.sortType)
    if(this.sortBy = ''){
      alert('Choose sort by');
    }else if (this.sortType = ''){
      alert('Choose sort type');
    }else{
      console.log('OK')
      let data = {

        clientId: this.user.id,
        sortBy: sortingBy,
        sortType: sortingType
      }
      console.log(data)

      this.service.sort(data).subscribe((response: any) => {
        this.reservations = response;
        this.corectDate();
      })
    }
  }

}
