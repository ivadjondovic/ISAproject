import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent implements OnInit {

  @Output() sidenavClose = new EventEmitter();

  isLogout: string
  role: any
  username: string
  isPasswordOk = true

  constructor(public router:Router) { }


  currentUser = localStorage.getItem('user')
  
  ngOnInit() {
    let userStrng = localStorage.getItem('user');
    if(userStrng) {
      let user = JSON.parse(userStrng);
      this.username = user.username;
      this.isPasswordOk = user.firstPasswordChanged
      console.log(user.firstPasswordChanged)
      this.role = user.userType;
    }
  }

  public onSidenavClose = (commandType: string) => {
   
    if(commandType == 'logout'){
      localStorage.clear();
      location.reload();
      
    this.router.navigate(['/homepage']);
    }
    this.sidenavClose.emit();
    
  }



}
