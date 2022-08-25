import { Component, OnInit } from '@angular/core';
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
  constructor(public service: FishingLessonService) { }

  ngOnInit(): void {
    this.service.getAll().subscribe((response: any) => {
      this.lessons = response;
      console.log(this.lessons)
    })
  }

  showMore(id: string) {
    //this.router.navigate(['/cottageInfo', id]);

  }

  search() {
    
  }

  sort(){
   
  }

}
