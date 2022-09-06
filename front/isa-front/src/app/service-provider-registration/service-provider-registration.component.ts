import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-service-provider-registration',
  templateUrl: './service-provider-registration.component.html',
  styleUrls: ['./service-provider-registration.component.css']
})
export class ServiceProviderRegistrationComponent implements OnInit {

  name = ""
  surname = ""
  email = ""
  phone = ""
  city = ""
  country = ""
  password = ""
  confirmPassword = ""
  type = ""
  address = ""
  explanation = ""
  constructor(private _snackBar: MatSnackBar, public service: UserService, public router: Router) { }

  ngOnInit(): void {
  }

  register() {
    console.log(this.type)


    if (this.password != this.confirmPassword) {
      this._snackBar.open('Passwords do not matches!.', 'Close', { duration: 2500 })
    } else if (this.name == "") {
      this._snackBar.open('Enter your name.', 'Close', { duration: 2500 })
    }
    else if (this.surname == "") {
      this._snackBar.open('Enter your surname.', 'Close', { duration: 2500 })
    }
    else if (this.email == "") {
      this._snackBar.open('Enter your email.', 'Close', { duration: 2500 })
    }
    else if (this.phone == "") {
      this._snackBar.open('Enter your phone.', 'Close', { duration: 2500 })
    }
    else if (this.city == "") {
      this._snackBar.open('Enter city.', 'Close', { duration: 2500 })
    }
    else if (this.country == "") {
      this._snackBar.open('Enter country.', 'Close', { duration: 2500 })
    }
    else if (this.address == "") {
      this._snackBar.open('Enter address.', 'Close', { duration: 2500 })
    }
    else if (this.password == "") {
      this._snackBar.open('Enter your password.', 'Close', { duration: 2500 })
    }
    else if (this.confirmPassword == "") {
      this._snackBar.open('Enter confirm password.', 'Close', { duration: 2500 })
    }
    else if (this.explanation == "") {
      this._snackBar.open('Enter explanation.', 'Close', { duration: 2500 })
    }
    else if (this.type == "") {
      this._snackBar.open('Select service provider type.', 'Close', { duration: 2500 })
    }
    else {


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
      }, error => {
        this._snackBar.open('Registration failed!', 'Close', {duration: 3000})});
      this.router.navigate(['/homepage'])

    }

  }



}
