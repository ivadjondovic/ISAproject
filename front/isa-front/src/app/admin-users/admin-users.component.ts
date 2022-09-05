import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DeleteAccountService } from '../services/delete-account.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-admin-users',
  templateUrl: './admin-users.component.html',
  styleUrls: ['./admin-users.component.css']
})
export class AdminUsersComponent implements OnInit {

  users: any[]

  constructor(public service: UserService, private _snackBar: MatSnackBar) {}

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
    }, error => {
      this._snackBar.open('Unable to delete! User has already been deleted!', 'Close', {duration: 2500})});
  }

}
