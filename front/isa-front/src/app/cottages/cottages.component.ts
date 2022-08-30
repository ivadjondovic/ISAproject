import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CottageService } from '../services/cottage.service';

@Component({
  selector: 'app-cottages',
  templateUrl: './cottages.component.html',
  styleUrls: ['./cottages.component.css']
})
export class CottagesComponent implements OnInit {

  cottages: any = {} as any;
  searchTerm = '';
  sortBy = '';
  sortType = '';
  user: any
  role: any
  addSearch = false
  dateForSearch: any
  constructor(public service: CottageService, public router: Router) { }


  ngOnInit(): void {
    this.service.getAll().subscribe((response: any) => {
      this.cottages = response;
      console.log(this.cottages)
      let userStrng = localStorage.getItem('user');
      if (userStrng) {
        this.user = JSON.parse(userStrng);
        this.role = this.user.userType;
      }
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

  sort(){
    console.log(this.sortBy)
    let sortingBy = this.sortBy
    let sortingType = this.sortType
    console.log(this.sortType)
    if(this.sortBy = ''){
      alert('Choose sort by');
    }else if (this.sortType = ''){
      alert('Choose sort type');
    }else{
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

  additionalSearch(){
    this.addSearch = true
  }

  searchByDate(){
    let data = {
      date: this.dateForSearch
    }
    this.service.getAvailableBoatsForCertainDate(data).subscribe((response: any) => {
      this.cottages = response;
     })
  }

}
