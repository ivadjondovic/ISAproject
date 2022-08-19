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
  constructor(public service: CottageService, public router: Router) { }


  ngOnInit(): void {
    this.service.getAll().subscribe((response: any) => {
      this.cottages = response;
      console.log(this.cottages)
    })
  }

  showMore(id: string){
    this.router.navigate(['/cottageInfo', id]);
  
  }

}
