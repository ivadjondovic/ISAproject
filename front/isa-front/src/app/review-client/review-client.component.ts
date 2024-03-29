import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { ClientReviewService } from '../services/client-review.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-review-client',
  templateUrl: './review-client.component.html',
  styleUrls: ['./review-client.component.css']
})
export class ReviewClientComponent implements OnInit {

  clientReview = ""
  penaltyType = ""
  id: any
  reservationId: any
  reservationType: any
  currentUser: any

  constructor(private _snackBar: MatSnackBar, private service: ClientReviewService, private activatedRoute: ActivatedRoute, private userService: UserService) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.id = params['id'];
      this.reservationId = params['reservationId'];
      this.reservationType = params['reservationType']
      this.userService.current().subscribe((response: any) => {
        this.currentUser = response;
      })
    })
  }

  submitReview() {
    let penaltyS = false;
    let aPenalty = false;

    if (this.penaltyType == "pentaltySuggestion") {
      penaltyS = true;
    }

    if (this.penaltyType == "automaticPenalty") {
      aPenalty = true;
    }

    if (this.clientReview == "") {
      this._snackBar.open('Passwords do not matches!.', 'Close', { duration: 2500 })
    } else {

      let data = {
        id: this.reservationId,
        reservationType: this.reservationType,
        clientId: this.id,
        instructorId: this.currentUser.id,
        penaltySuggestion: penaltyS,
        penaltySuggestionReason: this.clientReview,
        automaticPenalty: aPenalty
      }

      this.service.createReview(data).subscribe((response: any) => {
        console.log(response);
        location.reload();
      }, error => {
        this._snackBar.open('Client review failed!', 'Close', {duration: 3000})});

    }

  }

}
