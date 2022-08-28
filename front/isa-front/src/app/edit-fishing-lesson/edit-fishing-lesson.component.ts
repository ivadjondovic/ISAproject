import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FishingLessonService } from '../services/fishing-lesson.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-edit-fishing-lesson',
  templateUrl: './edit-fishing-lesson.component.html',
  styleUrls: ['./edit-fishing-lesson.component.css']
})
export class EditFishingLessonComponent implements OnInit {

  user: any
  id: string
  lesson: any
  newRuleDescription: any
  rule = false
  equipment = false
  quickReservations = false
  additionalServices = false
  availablePeriods = false
  deleteRule = false
  deleteEquipment = false
  deleteQuickReservations = false
  deleteAdditionalServices = false
  deleteAvailablePeriods = false
  quickStartDate: any
  quickEndDate: any
  price: any
  numberOfGuests: any
  quickAdditionalServices: any
  location: any
  newServiceDescription: any
  servicePrice: any
  periodStartDate: any 
  periodEndDate: any
  existingRules: any[]
  existingFishingEquipment: any[]
  existingQuickReservations: any[]
  existingAdditionalServices: any[]
  existingAvailablePeriods: any[]
  dateList: any[]
  dates: any[]
  quickReservationList: any[]
  quickReservation: any[]


  constructor(public service: FishingLessonService, public userService: UserService, public activatedRoute: ActivatedRoute,) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.id = params['id'];
      console.log(this.id);
      this.userService.current().subscribe((response: any) => {
        this.user = response;
        this.service.getById(this.id).subscribe((response: any) => {
          this.lesson = response;
          this.existingRules = this.lesson.rules;
          this.existingAdditionalServices = this.lesson.additionalServices;
          this.existingAvailablePeriods = this.lesson.availablePeriods;
          this.existingFishingEquipment = this.lesson.fishingEquipment;
          console.log(this.existingFishingEquipment);
          this.existingQuickReservations = this.lesson.quickReservations;
          this.convertToDate();
          this.corectDate();
        })
      })
    })
  }

  addNewAvailablePeriods() {
    this.rule = false;
    this.equipment = false;
    this.quickReservations = false;
    this.additionalServices = false;
    this.availablePeriods = true;
    this.deleteEquipment = false;
    this.deleteQuickReservations = false;
    this.deleteAvailablePeriods = false;
    this.deleteAdditionalServices = false;
    this.deleteRule = false;
  }

  editAvailablePeriods() {
    this.rule = false;
    this.equipment = false;
    this.quickReservations = false;
    this.additionalServices = false;
    this.availablePeriods = false;
    this.deleteRule = false;
    this.deleteEquipment = false;
    this.deleteQuickReservations = false;
    this.deleteAdditionalServices = false;
    this.deleteAvailablePeriods = true;
  }


  addNewAdditionalServices() {
    this.availablePeriods = false;
    this.rule = false;
    this.equipment = false;
    this.quickReservations = false;
    this.additionalServices = true;
    this.deleteEquipment = false;
    this.deleteQuickReservations = false;
    this.deleteAvailablePeriods = false;
    this.deleteAdditionalServices = false;
    this.deleteRule = false;
  }

  editAdditionalServices() {
    this.rule = false;
    this.equipment = false;
    this.quickReservations = false;
    this.additionalServices = false;
    this.availablePeriods = false;
    this.deleteRule = false;
    this.deleteEquipment = false;
    this.deleteQuickReservations = false;
    this.deleteAvailablePeriods = false;
    this.deleteAdditionalServices = true;
  }

  addNewQuickReservations() {
    this.additionalServices = false;
    this.availablePeriods = false;
    this.rule = false;
    this.equipment = false;
    this.quickReservations = true;
    this.deleteEquipment = false;
    this.deleteQuickReservations = false;
    this.deleteAvailablePeriods = false;
    this.deleteAdditionalServices = false;
    this.deleteRule = false;
  }

  editQuickReservations() {
    this.rule = false;
    this.equipment = false;
    this.quickReservations = false;
    this.additionalServices = false;
    this.availablePeriods = false;
    this.deleteRule = false;
    this.deleteEquipment = false;
    this.deleteAvailablePeriods = false;
    this.deleteAdditionalServices = false;
    this.deleteQuickReservations = true;
  }

  addNewFishingEquipment() {
    this.additionalServices = false;
    this.availablePeriods = false;
    this.quickReservations = false;
    this.rule = false;
    this.equipment = true;
    this.deleteEquipment = false;
    this.deleteQuickReservations = false;
    this.deleteAvailablePeriods = false;
    this.deleteAdditionalServices = false;
    this.deleteRule = false;
  }

  editFishingEquipment() {
    this.rule = false;
    this.equipment = false;
    this.quickReservations = false;
    this.additionalServices = false;
    this.availablePeriods = false;
    this.deleteRule = false;
    this.deleteQuickReservations = false;
    this.deleteAvailablePeriods = false;
    this.deleteAdditionalServices = false;
    this.deleteEquipment = true;
  }

  addNewRules() {
    this.additionalServices = false;
    this.availablePeriods = false;
    this.equipment = false;
    this.quickReservations = false;
    this.rule = true;
    this.deleteEquipment = false;
    this.deleteQuickReservations = false;
    this.deleteAvailablePeriods = false;
    this.deleteAdditionalServices = false;
    this.deleteRule = false;
  }

  editRules() {
    this.rule = false;
    this.equipment = false;
    this.quickReservations = false;
    this.additionalServices = false;
    this.availablePeriods = false;
    this.deleteEquipment = false;
    this.deleteQuickReservations = false;
    this.deleteAvailablePeriods = false;
    this.deleteAdditionalServices = false;
    this.deleteRule = true;
  }

  corectDate() {

    this.quickReservationList = [];
    for (let r of this.existingQuickReservations) {
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
    for (let d of this.existingAvailablePeriods) {
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

}
