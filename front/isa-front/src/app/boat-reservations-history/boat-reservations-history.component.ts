import { Component, OnInit } from '@angular/core';
import { BoatReservationService } from '../services/boat-reservation.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-boat-reservations-history',
  templateUrl: './boat-reservations-history.component.html',
  styleUrls: ['./boat-reservations-history.component.css']
})
export class BoatReservationsHistoryComponent implements OnInit {

  reservations: any[]
  reservationList: any[]
  user: any
  constructor(public service: BoatReservationService, public userService: UserService) { }

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
      let boat = r.boat;

      let data = {
        id: id,
        boat: boat,
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
