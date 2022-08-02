import { ReservationService } from './../../services/reservation.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder,  FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-boat-quick-reservation',
  templateUrl: './new-boat-quick-reservation.component.html',
  styleUrls: ['./new-boat-quick-reservation.component.css']
})
export class NewBoatQuickReservationComponent implements OnInit {
  validateForm!: FormGroup;
  public user: any;

  constructor(private router:Router,private fb: FormBuilder, private reservationService: ReservationService) {}


  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }

  const body = {
    startDateReservation: this.validateForm.value.startDateReservation,
    endDateReservation: this.validateForm.value.endDateReservation,
    startTimeReservation: this.validateForm.value.startTimeReservation,
    endTimeReservation: this.validateForm.value.endTimeReservation,
    reservationType: "BOAT",
    idOfType: this.user.id,
    maxNumberOfPeople: this.validateForm.value.maxNumberOfPeople,
    price:this.validateForm.value.price,
    expiresIn:this.validateForm.value.expiresIn
  }
  this.reservationService.createReservation(body).subscribe(data => {
    this.router.navigateByUrl(`home-page`);   
  }, error => {
  })
  }

  ngOnInit(): void {
    this.setupUser();
    this.validateForm = this.fb.group({
      startDateReservation: [null, [ Validators.required]],
      endDateReservation: [null, [Validators.required]],
      startTimeReservation: [null, [Validators.required]],
      endTimeReservation: [null, [Validators.required]],
      maxNumberOfPeople: [null, [ Validators.required]],
      price: [null, [Validators.required]],
      expiresIn: [null, [Validators.required]]
    });
  }

  private setupUser(): void {
    this.user = JSON.parse(localStorage.getItem('user'));
  } 
}
