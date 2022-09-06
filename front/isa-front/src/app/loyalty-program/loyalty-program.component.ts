import { Component, OnInit } from '@angular/core';
import { LoyaltyProgramService } from '../services/loyalty-program.service';

@Component({
  selector: 'app-loyalty-program',
  templateUrl: './loyalty-program.component.html',
  styleUrls: ['./loyalty-program.component.css']
})
export class LoyaltyProgramComponent implements OnInit {

  clientPointsPerReservation: ""
	ownerPointsPerReservation: ""
	pointsNeededForSilver: ""
	pointsNeededForGold: ""
	clientDiscountForSilver: ""
	clientDiscountForGold: ""
	ownerBonusForSilver: ""
	ownerBonusForGold: ""

  constructor(private service: LoyaltyProgramService) { }

  ngOnInit(): void {
  }

  save() {
    let data = {
      clientPointsPerReservation: this.clientPointsPerReservation,
      ownerPointsPerReservation: this.ownerPointsPerReservation,
      pointsNeededForSilver: this.pointsNeededForSilver,
      pointsNeededForGold: this.pointsNeededForGold,
      clientDiscountForSilver: this.clientDiscountForSilver,
      clientDiscountForGold: this.clientDiscountForGold,
      ownerBonusForSilver: this.ownerBonusForSilver,
      ownerBonusForGold: this.ownerBonusForGold
    }

    this.service.createLoyaltyProgram(data).subscribe((response: any) => {
      console.log(response)
    })
  }

}
