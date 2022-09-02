import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DeleteAccountService } from '../services/delete-account.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-delete-account',
  templateUrl: './delete-account.component.html',
  styleUrls: ['./delete-account.component.css']
})
export class DeleteAccountComponent implements OnInit {

  reason = ""
  user: any = {} as any
  constructor(private _snackBar: MatSnackBar, public service: DeleteAccountService, public userService: UserService) { }

  ngOnInit(): void {
    this.userService.current().subscribe((response: any) => {
      this.user = response;
    })

  }

  deleteAccount() {

    if (this.reason == "") {
      this._snackBar.open('Enter deletion request.', 'Close', { duration: 2500 })
    } else {
      let data = {
        "reason": this.reason,
        "userId": this.user.id
      }
      this.service.addRequest(data).subscribe((response: any) => {
        console.log(response);
        location.reload();
      })

    }
  }

}
