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
  constructor(public router:Router) { }

  currentUser = localStorage.getItem('user')
  
  ngOnInit() {
   
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
