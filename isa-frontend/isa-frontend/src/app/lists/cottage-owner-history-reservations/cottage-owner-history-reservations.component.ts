import { ReservationService } from './../../services/reservation.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-cottage-owner-history-reservations',
  templateUrl: './cottage-owner-history-reservations.component.html',
  styleUrls: ['./cottage-owner-history-reservations.component.css']
})
export class CottageOwnerHistoryReservationsComponent implements OnInit {

  public user: any;
  public cottageOwnerId: any;
  public reservations = [];
  validateForm!: FormGroup;
  public alert = true;
  public alertSucc = false;
  public alertDelete = false;

  constructor(private reservationService: ReservationService, private router: Router,  private fb: FormBuilder) { }

  ngOnInit(): void {
    this.setupUser();
    this.getAllReservationsByCottageOwnerId(this.cottageOwnerId);

    this.validateForm = this.fb.group({
      name: [""],
      type: [""]
    });
  }

  private setupUser(): void {
    this.user = JSON.parse(localStorage.getItem('user')!);
    this.cottageOwnerId = this.user.id;
  } 

  public report(id): void {
  } 

  private getAllReservationsByCottageOwnerId(id): void {
    this.reservationService.getAllReservationsByCottageOwnerId(id).subscribe(data => {
      this.reservations = data;
      console.log(data);
    }, error => {
      alert("Error");
    })
  }

}
