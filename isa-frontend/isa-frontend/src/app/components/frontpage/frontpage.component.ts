import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-frontpage',
  templateUrl: './frontpage.component.html',
  styleUrls: ['./frontpage.component.css']
})
export class FrontpageComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  loginForm():void{
    this.router.navigate(['front-page/login-form']);
  }

  registrationForm():void{
    this.router.navigate(['front-page/registration-form']);
  }

  pharmacies(): void{
    this.router.navigate(['front-page/pharmacies-nonauth']);
  }

}
