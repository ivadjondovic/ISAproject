import { Component, OnInit } from '@angular/core';
import { ClientReviewService } from '../services/client-review.service';

@Component({
  selector: 'app-admin-penalties',
  templateUrl: './admin-penalties.component.html',
  styleUrls: ['./admin-penalties.component.css']
})
export class AdminPenaltiesComponent implements OnInit {

  reviews: any[]

  constructor(private service: ClientReviewService) { }

  ngOnInit(): void {
    this.service.notCheckedPenalties().subscribe((response: any) => {
      this.reviews = response;
    })
  }

  acceptPenalty(id: any, clientId: any, instructorId: any) {
    let data = {
      id: id,
      clientId: clientId,
      instructorId: instructorId
    }
    this.service.acceptPenalty(data).subscribe((response: any) => {
      console.log(response)
      this.service.notCheckedPenalties().subscribe((response: any) => {
        this.reviews = response;
      })
    })
  }

  declinePenalty(id: any, clientId: any, instructorId: any) {
    let data = {
      id: id,
      clientId: clientId,
      instructorId: instructorId
    }
    this.service.declinePenalty(data).subscribe((response: any) => {
      console.log(response)
      this.service.notCheckedPenalties().subscribe((response: any) => {
        this.reviews = response;
      })
    })
  }

}
