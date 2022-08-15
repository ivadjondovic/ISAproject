import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-admin-registration',
  templateUrl: './admin-registration.component.html',
  styleUrls: ['./admin-registration.component.css']
})
export class AdminRegistrationComponent implements OnInit {

  name: string
  surname: string;
  email: string;
  phone: string;
  city: string;
  country: string;
  address: string;
  constructor(public service: UserService, public router: Router) { }

  ngOnInit(): void {
  }

  registerAdmin() {

    let data = {
      username: this.email,
      name: this.name,
      surname: this.surname,
      address: this.address,
      city: this.city,
      country: this.country,
      phoneNumber: this.phone,
      type: 'Admin'

    }
    this.service.register(data).subscribe((response: any) => {
      console.log(response)
    })
    this.router.navigate(['/homepage'])

  }

}


