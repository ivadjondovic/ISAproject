import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdditionalServiceService } from '../services/additional-service.service';
import { AvailablePeriodService } from '../services/available-period.service';
import { FishingEquipmentService } from '../services/fishing-equipment.service';
import { FishingLessonService } from '../services/fishing-lesson.service';
import { ImageService } from '../services/image.service';
import { QuickFishingReservationService } from '../services/quick-fishing-reservation.service';
import { RuleService } from '../services/rule.service';
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
  newEquipmentDescription: any
  rule = false
  equipment = false
  quickReservations = false
  additionalServices = false
  availablePeriods = false
  images = false
  deleteRule = false
  deleteEquipment = false
  deleteQuickReservations = false
  deleteAdditionalServices = false
  deleteAvailablePeriods = false
  deleteImage = false
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
  existingImages: any[]
  dateList: any[]
  dates: any[]
  quickReservationList: any[]
  quickReservation: any[]

  url: any;
  msg = "";
  public img = "";


  constructor(public ruleService: RuleService, 
              public service: FishingLessonService, 
              public userService: UserService, 
              public activatedRoute: ActivatedRoute,
              public periodService: AvailablePeriodService,
              public additionalServiceService: AdditionalServiceService,
              public quickReservationService: QuickFishingReservationService,
              public equipmentService: FishingEquipmentService,
              public imageService: ImageService
              ) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.id = params['id'];
      console.log(this.id);
      this.userService.current().subscribe((response: any) => {
        this.user = response;
        this.service.getByIdForInstructor(this.id).subscribe((response: any) => {
          this.lesson = response;
          this.existingRules = this.lesson.rules;
          this.existingAdditionalServices = this.lesson.additionalServices;
          this.existingAvailablePeriods = this.lesson.availablePeriods;
          this.existingFishingEquipment = this.lesson.fishingEquipment;
          this.existingImages = this.lesson.images;
          console.log(this.existingFishingEquipment);
          this.existingQuickReservations = this.lesson.quickReservations;
          this.convertToDate();
          this.corectDate();
          console.log(this.quickReservationList);
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
    this.images = false;
    this.deleteImage = false;
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
    this.images = false;
    this.deleteImage = false;
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
    this.images = false;
    this.deleteImage = false;
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
    this.images = false;
    this.deleteImage = false;
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
    this.images = false;
    this.deleteImage = false;
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
    this.images = false;
    this.deleteImage = false;
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
    this.images = false;
    this.deleteImage = false;
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
    this.images = false;
    this.deleteImage = false;
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
    this.images = false;
    this.deleteImage = false;
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
    this.images = false;
    this.deleteImage = false;
  }

  corectDate() {

    this.quickReservationList = [];
    for (let r of this.existingQuickReservations) {
      let startDate = new Date(r.startDate[0], r.startDate[1] - 1, r.startDate[2], r.startDate[3], r.startDate[4]).toISOString().slice(0, 16);
      let endDate = new Date(r.endDate[0], r.endDate[1] - 1, r.endDate[2], r.endDate[3], r.endDate[4]).toISOString().slice(0, 16);
      let price = r.price;
      let location = r.location;
      let id = r.id;
      let maxNumberOfPerson = r.maxNumberOfPerson;
      let additionalServices = r.additionalServices;

      let data = {
        startDate: startDate,
        endDate: endDate,
        price: price,
        maxNumberOfPerson: maxNumberOfPerson,
        location: location,
        additionalServices: additionalServices,
        id: id
      }
      this.quickReservationList.push(data);
      console.log(startDate)

    }
  }

  convertToDate() {

    this.dateList = [];
    for (let d of this.existingAvailablePeriods) {
      let startDate = new Date(d.startDate[0], d.startDate[1] - 1, d.startDate[2], d.startDate[3], d.startDate[4]).toISOString().slice(0, 16);
      let endDate = new Date(d.endDate[0], d.endDate[1] - 1, d.endDate[2], d.endDate[3], d.endDate[4]).toISOString().slice(0, 16);
      let id = d.id;
      console.log(startDate)
      let newDate = {
        startDate: startDate,
        endDate: endDate,
        id: id
      }
      console.log(newDate)
      this.dateList.push(newDate)

    }
    console.log(this.dateList)
  }

  deletePeriod(id: any) {
    this.periodService.delete(id).subscribe((response: any) => {
      location.reload();
    })
  }

  deleterule(id: any) {
    this.ruleService.delete(id , this.id).subscribe((response: any) => {
      location.reload();
    })
  }

  deleteequipment(id: any) {
    this.equipmentService.delete(id).subscribe((response: any) => {
      location.reload();
    })
  }

  deleteReservation(id: any) {
    this.quickReservationService.delete(id).subscribe((response: any) => {
      location.reload();
    })
  }

  deleteService(id: any) {
    this.additionalServiceService.delete(id).subscribe((response: any) => {
      location.reload();
    })
  }

  savePeriod(id: any, startDate: any, endDate: any) {
    let data = {
      id: id,
      startDate: startDate,
      endDate: endDate
    }
    this.periodService.save(data).subscribe((response: any) => {
      console.log(response);
      location.reload();
    })
  }

  saveService(id: any, description: any, price: any) {
    let data = {
      id: id,
      description: description,
      price: price
    }
    this.additionalServiceService.save(data).subscribe((response: any) => {
      console.log(response);
      location.reload();
    })
  }

  saveReservation(id: any, startDate: any, endDate: any, price: any, rlocation: any, maxNumberOfPerson: any, additionalServices: any) {
    let data = {
      id: id,
      startDate: startDate,
      endDate: endDate,
      price: price,
      location: rlocation,
      maxNumberOfPerson: maxNumberOfPerson,
      additionalServices: additionalServices
    }
    this.quickReservationService.save(data).subscribe((response: any) => {
      console.log(response);
      location.reload();
    })
  
  }

  saveEquipment(id: any, description: any) {
    let data = {
      id: id,
      description: description
    }
    this.equipmentService.save(data).subscribe((response: any) => {
      console.log(response);
      location.reload();
    })

  }

  saveRule(id: any, description: any) {
    let data = {
      id: id,
      description: description
    }
    this.ruleService.save(data).subscribe((response: any) => {
      console.log(response);
      location.reload();
    })

  }

  addPeriod() {
    let data = {
      id: this.lesson.id,
      startDate: this.periodStartDate,
      endDate: this.periodEndDate
    }
    this.periodService.add(data).subscribe((response: any) => {
      console.log(response);
      location.reload();
    })
  }

  addRule() {
    let data = {
      id: this.lesson.id,
      description: this.newRuleDescription
    }
    this.ruleService.add(data).subscribe((response: any) => {
      console.log(response);
      location.reload();
    })
  }

  addEquipment() {
    let data = {
      id: this.lesson.id,
      description: this.newEquipmentDescription
    }
    this.equipmentService.add(data).subscribe((response: any) => {
      console.log(response);
      location.reload();
    })
  }

  addReservation() {
    let data = {
      id: this.lesson.id,
      startDate: this.quickStartDate,
      endDate: this.quickEndDate,
      price: this.price,
      location: this.location,
      maxNumberOfPerson: this.numberOfGuests,
      additionalServices: this.quickAdditionalServices
    }
    this.quickReservationService.add(data).subscribe((response: any) => {
      console.log(response);
      location.reload();
    })
  }

  addService() {
    let data = {
      id: this.lesson.id,
      description: this.newServiceDescription,
      price: this.servicePrice
    }
    this.additionalServiceService.add(data).subscribe((response: any) => {
      console.log(response);
      location.reload();
    })

  }


  selectFile(event: any) {
    if (!event.target.files[0] || event.target.files[0].length == 0) {
      this.msg = 'You must select an image';
      return;
    }

    var mimeType = event.target.files[0].type;

    if (mimeType.match(/image\/*/) == null) {
      this.msg = "Only images are supported";
      return;
    }

    var reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    this.img = event.target.files[0].name
    console.log(this.img)

    reader.onload = (_event) => {
      this.msg = "";
      this.url = reader.result;
    }
  }

  addImage() {
    let data = {
      id: this.lesson.id,
      path: "assets/" + this.img
    }
    this.imageService.add(data).subscribe((response: any) => {
      console.log(response);
      location.reload();
    })

  }

  addNewImages() {
    this.additionalServices = false;
    this.availablePeriods = false;
    this.equipment = false;
    this.quickReservations = false;
    this.rule = false;
    this.deleteEquipment = false;
    this.deleteQuickReservations = false;
    this.deleteAvailablePeriods = false;
    this.deleteAdditionalServices = false;
    this.deleteRule = false;
    this.images = true;
    this.deleteImage = false;
  }

  editImages() {
    this.additionalServices = false;
    this.availablePeriods = false;
    this.equipment = false;
    this.quickReservations = false;
    this.rule = false;
    this.deleteEquipment = false;
    this.deleteQuickReservations = false;
    this.deleteAvailablePeriods = false;
    this.deleteAdditionalServices = false;
    this.deleteRule = false;
    this.images = false;
    this.deleteImage = true;
  }

  deleteimage(id: any) {
    this.imageService.delete(id, this.lesson.id).subscribe((response: any) => {
      location.reload();
    })
  }


}
