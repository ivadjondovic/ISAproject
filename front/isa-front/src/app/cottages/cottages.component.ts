import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { CottageSubscriptionService } from '../services/cottage-subscription.service';
import { CottageService } from '../services/cottage.service';

@Component({
  selector: 'app-cottages',
  templateUrl: './cottages.component.html',
  styleUrls: ['./cottages.component.css']
})
export class CottagesComponent implements OnInit {

  cottages: any[];
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
  subscribedCottages: any[]
  isLoged = false;
  constructor(private _snackBar: MatSnackBar, public subscriptionService: CottageSubscriptionService, public service: CottageService, public router: Router) { }


  ngOnInit(): void {
    this.subscribedCottages = [];
    this.service.getAll().subscribe((response: any) => {
      this.cottages = response;
      console.log(this.cottages)
      let userStrng = localStorage.getItem('user');
      if (userStrng) {
        this.user = JSON.parse(userStrng);
        this.role = this.user.userType;
        this.service.subscribedCottages(this.user.id).subscribe((response: any) => {
          this.subscribedCottages = response;
          this.isLoged = true
        })
      }

    })
  }

  isSubscribed(id: any) {

    if (this.isLoged == false) {
      return true
    }

    let userStrng = localStorage.getItem('user');
    if (userStrng) {

      for (let i = 0; i < this.subscribedCottages.length; i++) {
        if (this.subscribedCottages[i].id == id) {
          return true;
        }
      }
      return false;
    }
    return true


  }

  subscribe(id: any) {
    let data = {
      clientId: this.user.id,
      entityId: id
    }
    this.subscriptionService.subscribeEntity(data).subscribe((response: any) => {
      console.log(response)
      this.service.subscribedCottages(this.user.id).subscribe((response: any) => {
        this.subscribedCottages = response;
      })
    })

  }

  showMore(id: string) {
    this.router.navigate(['/cottageInfo', id]);

  }

  search() {
    if (this.searchTerm == '') {
      this.service.getAll().subscribe((response: any) => {
        this.cottages = response;
      })
    } else {
      this.service.search(this.searchTerm).subscribe((response: any) => {
        this.cottages = response;
      })

    }
  }

  sort() {
    console.log(this.sortBy)
    let sortingBy = this.sortBy
    let sortingType = this.sortType
    console.log(this.sortType)
    if(this.sortBy == ''){
      this._snackBar.open('Enter sort by.', 'Close', {duration: 2500});
    }else if (this.sortType == ''){
      this._snackBar.open('Enter sort type.', 'Close', {duration: 2500})
    } else {
      console.log('OK')
      let data = {
        sortBy: sortingBy,
        sortType: sortingType
      }
      console.log(data)

      this.service.sort(data).subscribe((response: any) => {
        this.cottages = response;
        console.log(this.cottages)
      })
    }
  }


  searchCottages(){
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
        this.cottages = response;
      })
    }

  }

}
