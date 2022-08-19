import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BoatService } from '../services/boat.service';

@Component({
  selector: 'app-boats',
  templateUrl: './boats.component.html',
  styleUrls: ['./boats.component.css']
})
export class BoatsComponent implements OnInit {

  boats: any = {} as any;
  constructor(public service: BoatService, public router: Router) { }


  ngOnInit(): void {
    this.service.getAll().subscribe((response: any) => {
      this.boats = response;
      console.log(this.boats)
    })
  }

  showMore(id: string){
    this.router.navigate(['/boatInfo', id]);
  }

}
