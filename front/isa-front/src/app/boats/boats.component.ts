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
  searchTerm = '';
  sortBy = '';
  sortType = '';
  constructor(public service: BoatService, public router: Router) { }


  ngOnInit(): void {
    this.service.getAll().subscribe((response: any) => {
      this.boats = response;
      console.log(this.boats)
    })
  }

  showMore(id: string) {
    this.router.navigate(['/boatInfo', id]);
  }

  search() {
    if (this.searchTerm == '') {
      this.service.getAll().subscribe((response: any) => {
        this.boats = response;
      })
    } else {
      this.service.search(this.searchTerm).subscribe((response: any) => {
        this.boats = response;
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
        this.boats = response;
        console.log(this.boats)
      })
    }
  }


}
