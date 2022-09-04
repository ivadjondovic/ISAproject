import { Component, OnInit } from '@angular/core';
import { DeleteAccountService } from '../services/delete-account.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-admin-users',
  templateUrl: './admin-users.component.html',
  styleUrls: ['./admin-users.component.css']
})
export class AdminUsersComponent implements OnInit {

  users: any[]

  constructor(public service: UserService) {}

  ngOnInit(): void {
    this.service.getUsers().subscribe((response: any) => {
      this.users = response;
    })
  }

  deleteUser(id: any) {
    this.service.deleteUser(id).subscribe((response: any) =>{
      console.log(response);
      this.service.getUsers().subscribe((response: any) => {
        this.users = response;
      })
    })
  }

}
