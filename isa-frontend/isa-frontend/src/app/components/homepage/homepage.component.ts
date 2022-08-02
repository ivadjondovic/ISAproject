import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  public isBoatOwner: boolean = false;
  public isCottageOwner: boolean = false;
  public isFishingInstructor: boolean = false;
  public isSystemAdmin: boolean = false;
  public isClient: boolean = false;
  public user: any;


  constructor(private router:Router) { }

  ngOnInit(): void {
    this.setupUser();
    this.setupUserType();
  }

  private setupUser(): void {
    this.user = JSON.parse(localStorage.getItem('user'));
  } 
  
  private setupUserType(): void {
    console.log(this.user.userRole);
    if(this.user.userRole === 'SYSTEM_ADMIN'){
      this.isSystemAdmin = true;
    }else if(this.user.userRole === 'BOAT_OWNER'){
      this.isBoatOwner = true;
    }else if(this.user.userRole === 'COTTAGE_OWNER'){
      this.isCottageOwner = true;
    }else if(this.user.userRole === 'FISHING_INSTRUCTOR'){
      this.isFishingInstructor = true;
    }else if(this.user.userRole === 'CLIENT'){
      this.isClient = true;
    }
    
  }

  cottages(): void {
    this.router.navigateByUrl(`home-page/lists/owner-cottages/${this.user.id}`);
  }

  public changePasswordCottageOwner(): void {
    this.router.navigateByUrl(`home-page/updates/update-password-cottage-owner`);
  }

  public cottageOwnerProfile(): void {
    this.router.navigateByUrl(`home-page/updates/update-cottage-owner/${this.user.id}`);
  }


  boats(): void {
    this.router.navigateByUrl(`home-page/lists/owner-boats/${this.user.id}`);
  }

  public changePasswordBoatOwner(): void {
    this.router.navigateByUrl(`home-page/updates/update-password-boat-owner`);
  }

  public boatOwnerProfile(): void {
    this.router.navigateByUrl(`home-page/updates/update-boat-owner/${this.user.id}`);
  }

  public requestedDeletion(): void {
    this.router.navigateByUrl(`home-page/lists/requested-deletions`);
  }

  
  public requestForDeletion(): void {
    let type;
    if(this.user.userRole === 'COTTAGE_OWNER'){
      type = "co";
    }else if(this.user.userRole === 'BOAT_OWNER'){
      type = "bi";
    }else if(this.user.userRole === 'CLIENT'){
      type = "c";
    }else if(this.user.userRole === 'FISHING_INSTRUCTOR'){
      type = "fi";
    }
    this.router.navigateByUrl(`home-page/new-items/request-for-deletion/${type}`);
  }


  public seeHistoryOfReservation(): void {
    this.router.navigateByUrl(`home-page/lists/cottage-owner-history-of-reservations/${this.user.id}`);
  }
  
  public clearStorage(): void {
    localStorage.clear();
    this.router.navigateByUrl('front-page');
  }
}
