import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { FishingLessonSubscriptionService } from '../services/fishing-lesson-subscription.service';
import { FishingLessonService } from '../services/fishing-lesson.service';

@Component({
  selector: 'app-client-fishing-lessons',
  templateUrl: './client-fishing-lessons.component.html',
  styleUrls: ['./client-fishing-lessons.component.css']
})
export class ClientFishingLessonsComponent implements OnInit {

  lessons: any[];
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
  subscribedLessons: any[]
  isLoged = false;
  constructor(private _snackBar: MatSnackBar, public subscriptionService: FishingLessonSubscriptionService, public service: FishingLessonService, public router: Router) { }

  ngOnInit(): void {
    this.subscribedLessons = []
    this.service.getAll().subscribe((response: any) => {
      this.lessons = response;
      console.log(this.lessons)
      let userStrng = localStorage.getItem('user');
      if (userStrng) {
        this.user = JSON.parse(userStrng);
        this.role = this.user.userType;
        if(this.role == 'ROLE_CLIENT') {
          this.service.subscribedLessons(this.user.id).subscribe((response: any) => {
            this.subscribedLessons = response;
            this.isLoged = true
          })
        }
      }
    })
  }

  isSubscribed(id: any) {

    if (this.isLoged == false) {
      return true
    }

    let userStrng = localStorage.getItem('user');
    if (userStrng) {

      for (let i = 0; i < this.subscribedLessons.length; i++) {
        if (this.subscribedLessons[i].id == id) {
          return true;
        }
      }
      return false;
    }
    return true


  }

  deleteLesson(id: any) {
    console.log(id);
    this.service.deleteLesson(id).subscribe((response: any) => {
      this.service.getAll().subscribe((response: any) => {
        this.lessons = response;
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

  subscribe(id: any) {
    let data = {
      clientId: this.user.id,
      entityId: id
    }
    this.subscriptionService.subscribeEntity(data).subscribe((response: any) => {
      console.log(response)
      this.service.subscribedLessons(this.user.id).subscribe((response: any) => {
        this.subscribedLessons = response;
      })
    })

  }

  showMore(id: string) {
    this.router.navigate(['/lessonInfo', id]);

  }

  search() {
    if (this.searchTerm == '') {
      this.service.getAll().subscribe((response: any) => {
        this.lessons = response;
      })
    } else {
      this.service.search(this.searchTerm).subscribe((response: any) => {
        this.lessons = response;
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
      console.log('OK')
      let data = {
        sortBy: sortingBy,
        sortType: sortingType
      }
      console.log(data)

      this.service.sort(data).subscribe((response: any) => {
        this.lessons = response;
        console.log(this.lessons)
      })
    }
   
  }

  searchLessons(){
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
        this.lessons= response;
      })
    }

  }

}
