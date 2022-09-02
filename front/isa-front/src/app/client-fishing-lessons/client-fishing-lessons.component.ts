import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FishingLessonSubscriptionService } from '../services/fishing-lesson-subscription.service';
import { FishingLessonService } from '../services/fishing-lesson.service';

@Component({
  selector: 'app-client-fishing-lessons',
  templateUrl: './client-fishing-lessons.component.html',
  styleUrls: ['./client-fishing-lessons.component.css']
})
export class ClientFishingLessonsComponent implements OnInit {

  lessons: any = {} as any;
  searchTerm = '';
  sortBy = '';
  sortType = '';
  user: any
  role: any
  addSearch = false
  dateForSearch: any
  subscribedLessons: any[]
  isLoged = false;
  constructor(public subscriptionService: FishingLessonSubscriptionService, public service: FishingLessonService, public router: Router) { }

  ngOnInit(): void {
    this.subscribedLessons = []
    this.service.getAll().subscribe((response: any) => {
      this.lessons = response;
      console.log(this.lessons)
      let userStrng = localStorage.getItem('user');
      if (userStrng) {
        this.user = JSON.parse(userStrng);
        this.role = this.user.userType;
        this.service.subscribedLessons(this.user.id).subscribe((response: any) => {
          this.subscribedLessons = response;
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

      for (let i = 0; i < this.subscribedLessons.length; i++) {
        if (this.subscribedLessons[i].id == id) {
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
        this.lessons = response;
        console.log(this.lessons)
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
      this.lessons = response;
     })
  }

}
