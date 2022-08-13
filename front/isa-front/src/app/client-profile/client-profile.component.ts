import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-client-profile',
  templateUrl: './client-profile.component.html',
  styleUrls: ['./client-profile.component.css']
})
export class ClientProfileComponent implements OnInit {

  isChangePasswordClicked = false;
  user: any
  country: string
  oldPassword: string
  password: string
  newPassword = ""
  constructor(public service: UserService) { }

  ngOnInit(): void {
    this.service.current().subscribe((response: any) => {
      this.user = response;
      this.password = this.user.password;
    })
  }
  

  changePasswordClick(){
    this.isChangePasswordClicked =  true;
  }

  editClient(){
    console.log(this.password)
    console.log(this.user.country)
    let data = {
      username: this.user.username,
      password: this.newPassword,
      name: this.user.name,
      surname: this.user.surname,
      address: this.user.address,
      city: this.user.city,
      country: this.user.country,
      phoneNumber: this.user.phoneNumber
    }
    this.service.editClient(data).subscribe((response: any) => {
      console.log(response);
      location.reload();
    })
    
  }


}
