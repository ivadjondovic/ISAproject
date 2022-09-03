import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-client-info',
  templateUrl: './client-info.component.html',
  styleUrls: ['./client-info.component.css']
})
export class ClientInfoComponent implements OnInit {

  clientId: any
  user: any

  constructor(private activatedRoute: ActivatedRoute, private service: UserService) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.clientId = params['id'];
      this.service.getClient(this.clientId).subscribe((response: any) => 
      {
        this.user = response;
      }
      )
    })
  }

}
