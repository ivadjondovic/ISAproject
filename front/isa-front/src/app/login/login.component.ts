import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string
  password: string;
  user: any
  constructor(public service:UserService, public router: Router) { }

  ngOnInit(): void {
  }

  login(){

    let data = {
      username: this.username,
      password: this.password
    }

    this.service.login(data).subscribe((response: any) => {
      console.log(response);
      localStorage.setItem('token', response.accessToken);
      localStorage.setItem('expiresIn', response.expiresIn);
      this.service.current().subscribe((response: any) => {
        this.user = response;
        localStorage.setItem('user', JSON.stringify(this.user));
        console.log(this.user);
        location.reload();
        this.router.navigate(['/homepage'])
      })
    })
    
  }

}
