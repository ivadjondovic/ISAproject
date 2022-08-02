import { BoatService } from './../../services/boat.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder,  FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-boat',
  templateUrl: './boat.component.html',
  styleUrls: ['./boat.component.css']
})
export class BoatComponent implements OnInit {

  validateForm!: FormGroup;
  public addBoat = false;
  public user: any;

  constructor(private router:Router,private fb: FormBuilder, private boatService: BoatService) {}


  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }

    let body = {
      name:  this.validateForm.value.name,
      type: this.validateForm.value.type,
      address:  this.validateForm.value.address,
      description:  this.validateForm.value.description,
      rulesOfConduct:  this.validateForm.value.rulesOfConduct,
      priceList:  this.validateForm.value.priceList,
      length:  this.validateForm.value.length,
      engineNumber:  this.validateForm.value.engineNumber,
      enginePower:  this.validateForm.value.enginePower,
      maxSpeed:  this.validateForm.value.maxSpeed,
      capacity:  this.validateForm.value.capacity,
      fishingEquipment:  this.validateForm.value.fishingEquipment,
      cancellationReservationFee: this.validateForm.value.cancellationReservationFee,
      boatOwnerId: this.user.id
    }
  this.boatService.addBoat(body).subscribe(data => {
    this.addBoat = true;
    this.router.navigateByUrl(`home-page`);   
  }, error => {
  })
  }

  ngOnInit(): void {
    this.setupUser();
    this.validateForm = this.fb.group({
      name: [null, [Validators.required]],
      type: [null, [Validators.required]],
      address: [null, [Validators.required]],
      description: [null, [Validators.required]],
      rulesOfConduct: [null, [Validators.required]],
      priceList: [null, [Validators.required]],
      length: [null, [Validators.required]],
      engineNumber: [null, [Validators.required]],
      enginePower: [null, [Validators.required]],
      maxSpeed: [null, [Validators.required]],
      capacity: [null, [Validators.required]],
      fishingEquipment: [null, [Validators.required]],
      cancellationReservationFee: [null, [Validators.required]]
    });
  }

  private setupUser(): void {
    this.user = JSON.parse(localStorage.getItem('user'));
  } 

}
