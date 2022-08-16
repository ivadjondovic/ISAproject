import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-change-admin-password',
  templateUrl: './change-admin-password.component.html',
  styleUrls: ['./change-admin-password.component.css']
})
export class ChangeAdminPasswordComponent implements OnInit {

  newPassword: any
  oldPassword: any
  confirmPassword: any

  constructor(public service: UserService, public router: Router) { }

  ngOnInit(): void {


  }

  save() {

    if (this.newPassword != this.confirmPassword) {
      alert("Passwords do not matches!")
    } else {
      let data = {
        oldPassword: this.oldPassword,
        newPassword: this.newPassword
      }
      this.service.changePassword(data).subscribe((response: any) => {
        console.log(response)
        localStorage.clear();
        location.reload();
      })
      this.router.navigate(['/homepage'])


    }
  }

}
