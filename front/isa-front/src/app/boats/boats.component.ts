import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { BoatSubscriptionService } from '../services/boat-subscription.service';
import { BoatService } from '../services/boat.service';

@Component({
  selector: 'app-boats',
  templateUrl: './boats.component.html',
  styleUrls: ['./boats.component.css']
})
export class BoatsComponent implements OnInit {

  boats: any[];
  rating = ""
  place = ""
  priceFrom = ""
  priceTo = ""
  peopleFrom = ""
  peopleTo = ""
  searchTerm = '';
  sortBy = '';
  sortType = '';
  user: any
  role: any
  dateForSearch = ""
  subscribedBoats: any[]
  isLoged = false;
  constructor(private _snackBar: MatSnackBar, public subscriptionService: BoatSubscriptionService, public service: BoatService, public router: Router) { }


  ngOnInit(): void {
    this.subscribedBoats = []
    this.service.getAll().subscribe((response: any) => {
      this.boats = response;
      console.log(this.boats)
      let userStrng = localStorage.getItem('user');
      if (userStrng) {
        this.user = JSON.parse(userStrng);
        this.role = this.user.userType;
        if(this.role == 'ROLE_CLIENT') {
          this.service.subscribedBoats(this.user.id).subscribe((response: any) => {
            this.subscribedBoats = response;
            console.log(this.subscribedBoats)
            this.isLoged = true
          })
        }
      }

      
    })
  }

  isSubscribed(id: any){

    if(this.isLoged == false){
      return true
    }
    let userStrng = localStorage.getItem('user');
    if(userStrng){
      for(let i =0; i<this.subscribedBoats.length; i++){
        if(this.subscribedBoats[i].id == id){
          return true;
        }
       }
       return false;

    }
    return true;
 
   }

   deleteBoat(id: any) {
    console.log(id);
    this.service.deleteBoat(id).subscribe((response: any) => {
      this.service.getAll().subscribe((response: any) => {
        this.boats = response;
      })
    }) 
  }

  isAdmin() {
    let userStrng = localStorage.getItem('user');
    if(userStrng){
      if(this.role == 'ROLE_ADMIN')
       return true;

    }
    return false;
  }

 
   subscribe(id: any){
     let data = {
       clientId: this.user.id,
       entityId: id
     }
     this.subscriptionService.subscribeEntity(data).subscribe((response: any) => {
       console.log(response)
       this.service.subscribedBoats(this.user.id).subscribe((response: any) => {
         this.subscribedBoats = response;
       })
     })
     
   }

  showMore(id: string) {
    this.router.navigate(['/boatInfo', id]);
  }

  search() {
    if (this.searchTerm == '') {
      this.service.getAll().subscribe((response: any) => {
        this.boats = response;
      })
    } else {
      this.service.search(this.searchTerm).subscribe((response: any) => {
        this.boats = response;
      })
    }
  }

  sort(){
    console.log(this.sortBy)
    let sortingBy = this.sortBy
    let sortingType = this.sortType
    console.log(this.sortType)
    if(this.sortBy == ''){
      this._snackBar.open('Enter sort by.', 'Close', {duration: 2500});
    }else if (this.sortType == ''){
      this._snackBar.open('Enter sort type.', 'Close', {duration: 2500})
    }else{
      let data = {
        sortBy: sortingBy,
        sortType: sortingType
      }
      console.log(data)

      this.service.sort(data).subscribe((response: any) => {
        this.boats = response;
        console.log(this.boats)
      })
    }
  }


  searchByDate(){
    let data = {
      date: this.dateForSearch
    }
    this.service.getAvailableBoatsForCertainDate(data).subscribe((response: any) => {
      this.boats = response;
     })
  }

  searchBoats(){
    if(this.dateForSearch == ''){
      this._snackBar.open('Enter date.', 'Close', {duration: 2500});
    }else{

      let data = {
        date: this.dateForSearch,
        location: this.place,
        rating: this.rating,
        priceFrom: this.priceFrom,
        priceTo: this.priceTo,
        peopleFrom: this.peopleFrom,
        peopleTo: this.peopleTo
      }

      this.service.searchByManyParams(data).subscribe((response: any) => {
        this.boats = response;
      })
    }

  }

 


}
