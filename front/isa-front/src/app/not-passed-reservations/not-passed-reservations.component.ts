import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ReservationsService } from '../services/reservations.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-not-passed-reservations',
  templateUrl: './not-passed-reservations.component.html',
  styleUrls: ['./not-passed-reservations.component.css']
})
export class NotPassedReservationsComponent implements OnInit {

  reservations: any[]
  reservationList: any[]
  user: any
  constructor(private _snackBar: MatSnackBar, public service: ReservationsService, public userService: UserService) { }

  ngOnInit(): void {
    this.userService.current().subscribe((response: any) => {
      this.user = response;
      this.service.getClientReservations(this.user.id).subscribe((response: any) => {
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
      let entityName = r.entityName;
      let id = r.id;
      let reservationType = r.reservationType;
      let posibleToCancel = r.posibleToCancel

      let data = {
        id: id,
        entityName: entityName,
        startDate: startDate,
        endDate: endDate,
        price: price,
        reservationType: reservationType,
        posibleToCancel: posibleToCancel
      }
      this.reservationList.push(data);
      console.log(startDate)

    }

  }
  cancel(id: any, type: any){

    let data = {
      reservationId: id,
      reservationType: type
    }

    this.service.cancelReservation(data).subscribe((response: any) => {
      console.log(response)
      this.service.getClientReservations(this.user.id).subscribe((response: any) => {
        this.reservations = response;
        console.log(this.reservations)

        this.corectDate();
      })
    }, error => {
      this._snackBar.open('Cancellation failed!', 'Close', {duration: 3000})});

  }

}
