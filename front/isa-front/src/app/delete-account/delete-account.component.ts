import { Component, OnInit } from '@angular/core';
import { DeleteAccountService } from '../services/delete-account.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-delete-account',
  templateUrl: './delete-account.component.html',
  styleUrls: ['./delete-account.component.css']
})
export class DeleteAccountComponent implements OnInit {

  reason: string
  user: any = {} as any
  constructor(public service: DeleteAccountService, public userService: UserService) { }

  ngOnInit(): void {
    this.userService.current().subscribe((response: any) => {
      this.user = response;
    })

  }

  deleteAccount(){
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
