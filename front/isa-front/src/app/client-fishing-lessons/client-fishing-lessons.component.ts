import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
  constructor(public service: FishingLessonService, public router: Router) { }

  ngOnInit(): void {
    this.service.getAll().subscribe((response: any) => {
      this.lessons = response;
      console.log(this.lessons)
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

}
