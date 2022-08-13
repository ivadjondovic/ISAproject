import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-service-provider-registration',
  templateUrl: './service-provider-registration.component.html',
  styleUrls: ['./service-provider-registration.component.css']
})
export class ServiceProviderRegistrationComponent implements OnInit {

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
  explanation: string;
  constructor(public service: UserService, public router: Router) { }

  ngOnInit(): void {
  }

  register(){
      console.log(this.type)


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
        type: this.type,
        explanation: this.explanation

      }
      this.service.register(data).subscribe((response: any) => {
        console.log(response)
      })
      this.router.navigate(['/homepage'])

    }

  }

  

}
