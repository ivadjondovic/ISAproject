import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FishingLessonService } from '../services/fishing-lesson.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-fishing-lessons',
  templateUrl: './fishing-lessons.component.html',
  styleUrls: ['./fishing-lessons.component.css']
})
export class FishingLessonsComponent implements OnInit {

  fishingLessons: any = {} as any;
  user: any
  searchTerm = '';

  constructor(public service: FishingLessonService, public userService: UserService,  public router: Router) { }

  ngOnInit(): void {
    this.userService.current().subscribe((response: any) => {
      this.user = response;
      this.service.getByInstructorId(this.user.id).subscribe((response: any) => {
        this.fishingLessons = response;
        console.log(this.fishingLessons)
      })
    })
  }

  showMore(id: string) {
    this.router.navigate(['/editFishingLesson', id]);
  }

  search() {
    if (this.searchTerm == '') {
      this.service.getByInstructorId(this.user.id).subscribe((response: any) => {
        this.fishingLessons = response;
      })
    } else {
      this.service.searchForInstructor(this.searchTerm, this.user.id).subscribe((response: any) => {
        this.fishingLessons = response;
      })

    }
    
  }

}
