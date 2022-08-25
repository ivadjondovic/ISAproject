import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FishingLessonService } from '../services/fishing-lesson.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-edit-fishing-lesson',
  templateUrl: './edit-fishing-lesson.component.html',
  styleUrls: ['./edit-fishing-lesson.component.css']
})
export class EditFishingLessonComponent implements OnInit {

  user: any
  id: string
  lesson: any

  constructor(public service: FishingLessonService, public userService: UserService, public activatedRoute: ActivatedRoute,) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.id = params['id'];
      console.log(this.id);
      this.userService.current().subscribe((response: any) => {
        this.user = response;
        this.service.getById(this.id).subscribe((response: any) => {
          this.lesson = response;
        })
      })
    })
  }

}
