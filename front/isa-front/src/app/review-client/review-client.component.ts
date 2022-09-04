import { Component, OnInit } from '@angular/core';
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
  currentUser: any

  constructor(private service: ClientReviewService, private activatedRoute: ActivatedRoute, private userService: UserService) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.id = params['id'];
      this.userService.current().subscribe((response: any) => {
        this.currentUser = response;
      })
    })
  }

  submitReview() {
    let penaltyS = false;
    let aPenalty = false;

    if(this.penaltyType == "pentaltySuggestion") {
      penaltyS = true;
    }

    if(this.penaltyType == "automaticPenalty") {
      aPenalty = true;
    }

    let data = {
      clientId : this.id,
      instructorId: this.currentUser.id,
	    penaltySuggestion: penaltyS,
	    penaltySuggestionReason: this.clientReview,
	    automaticPenalty: aPenalty
    }

    this.service.createReview(data).subscribe((response: any) => {
      console.log(response);
      location.reload();
    })

  }

}
