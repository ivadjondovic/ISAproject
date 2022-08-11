import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  name: string
  surname: string;
  email: string;
  phone: string;
  city: string;
  country: string;
  password: string; 
  confirmPassword: string;
  type: string;
  address: string;
  constructor(public service: UserService, public router: Router) { }

  ngOnInit(): void {
  }

  register(){

    if (this.password != this.confirmPassword){
      alert("Passwords do not matches!")
    }else{

      let data = {
        username: this.email,
        password: this.password, 
        name: this.name,
        surname: this.surname,
        address: this.address,
        city: this.city,
        country: this.country,
        phoneNumber: this.phone,
        type: this.type

      }
      this.service.register(data).subscribe((response: any) => {
        console.log(response)
      })
      this.router.navigate(['/homepage'])

    }

  }

}
