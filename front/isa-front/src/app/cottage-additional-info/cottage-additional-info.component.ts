import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CottageService } from '../services/cottage.service';

@Component({
  selector: 'app-cottage-additional-info',
  templateUrl: './cottage-additional-info.component.html',
  styleUrls: ['./cottage-additional-info.component.css']
})
export class CottageAdditionalInfoComponent implements OnInit {

  id: string
  cottage: any = {} as any
  dateList: any[]
  dates: any[]
  quickReservationList: any[]
  quickReservations: any[]
  constructor(public activatedRoute: ActivatedRoute, public service: CottageService) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.id = params['id'];
      console.log(this.id);
      this.service.getById(this.id).subscribe((response: any) => {
        this.cottage = response;
        this.dates = this.cottage.availablePeriods;
        this.quickReservations = this.cottage.quickReservations;
        this.convertToDate();
        this.corectDate();
        console.log(this.cottage)
      })

    });
  }

  corectDate() {

    this.quickReservationList = [];
    for (let r of this.quickReservations) {
      let startDate = new Date(r.startDate[0], r.startDate[1] - 1, r.startDate[2], r.startDate[3], r.startDate[4]);
      let endDate = new Date(r.endDate[0], r.endDate[1] - 1, r.endDate[2], r.endDate[3], r.endDate[4]);
      let price = r.price;
      let maxNumberOfPerson = r.maxNumberOfPerson;
      let additionalServices = r.additionalServices;

      let data = {
        startDate: startDate,
        endDate: endDate,
        price: price,
        maxNumberOfPerson: maxNumberOfPerson,
        additionalServices: additionalServices
      }
      this.quickReservationList.push(data);
      console.log(startDate)

    }
  }

  convertToDate() {

    this.dateList = [];
    for (let d of this.dates) {
      let startDate = new Date(d.startDate[0], d.startDate[1] - 1, d.startDate[2], d.startDate[3], d.startDate[4]);
      let endDate = new Date(d.endDate[0], d.endDate[1] - 1, d.endDate[2], d.endDate[3], d.endDate[4]);
      console.log(startDate)
      let newDate = {
        startDate: startDate,
        endDate: endDate
      }
      console.log(newDate)
      this.dateList.push(newDate)

    }
    console.log(this.dateList)
  }

  isEmpty(list: any[]){
    if(list.length == 0){
      return true;
    }else {
      return false;
    }
  }

}