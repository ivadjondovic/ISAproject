import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { BoatComplaintDialogComponent } from '../boat-complaint-dialog/boat-complaint-dialog.component';
import { RatingBoatDialogComponent } from '../rating-boat-dialog/rating-boat-dialog.component';
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
  sortBy: string
  sortType: string
  id: any
  constructor(public dialog: MatDialog, public service: BoatReservationService, public userService: UserService) { }

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
      let possibleToRate = r.possibleToRate
      let reservationType = r.reservationType

      let data = {
        id: id,
        boat: boat,
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

  rate(enterAnimationDuration: string, exitAnimationDuration: string, id: any, type: any) {
    this.id = id;
    const dialogRef = this.dialog.open(RatingBoatDialogComponent, {
      width: '45%',
      data: {
        id: this.id,
        type: type
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.id = result;
    });
  }

  complaint(enterAnimationDuration: string, exitAnimationDuration: string, id: any, type: any) {
    this.id = id;
    const dialogRef = this.dialog.open(BoatComplaintDialogComponent, {
      width: '45%',
      data: {
        id: this.id,
        type: type
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.id = result;
    });
  }


}
