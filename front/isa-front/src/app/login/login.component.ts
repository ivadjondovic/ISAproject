import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
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
  constructor(private _snackBar: MatSnackBar, public service:UserService, public router: Router) { }

  ngOnInit(): void {
  }

  login(){

    let data = {
      username: this.username,
      password: this.password
    }

    this.service.login(data).subscribe((response: any) => {
      localStorage.setItem('token', response.accessToken);
      localStorage.setItem('expiresIn', response.expiresIn);
      this.service.current().subscribe((response: any) => {
        this.user = response;
        localStorage.setItem('user', JSON.stringify(this.user));
        console.log(this.user);
        location.reload();
        
      })
    }, error => {
      this._snackBar.open('Incorrect credentials! Please check if you have activated your profile and then try again', 'Close', {duration: 2500})});
    
  }

}
