import { Component, OnInit } from '@angular/core';
import { SubscriptionsService } from '../services/subscriptions.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-client-subscriptions',
  templateUrl: './client-subscriptions.component.html',
  styleUrls: ['./client-subscriptions.component.css']
})
export class ClientSubscriptionsComponent implements OnInit {

  subscriptions: any[]
  user: any
  constructor(public service: SubscriptionsService, public userService: UserService) { }

  ngOnInit(): void {
    this.userService.current().subscribe((response: any) => {
      this.user = response;
      this.service.getClientSubscriptions(this.user.id).subscribe((response: any) => {
        this.subscriptions = response;
      })
    })
    
  }

}
