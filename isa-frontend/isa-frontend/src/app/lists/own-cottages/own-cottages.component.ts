import { SearchService } from './../../services/search.service';
import { CottageService } from './../../services/cottage.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-own-cottages',
  templateUrl: './own-cottages.component.html',
  styleUrls: ['./own-cottages.component.css']
})
export class OwnCottagesComponent implements OnInit {

  public user: any;
  public cottageOwnerId: any;
  public ownCottages = [];
  validateForm!: FormGroup;
  public alert = true;
  public alertSucc = false;
  public alertDelete = false;

  constructor(private cottageService: CottageService, private router: Router, private searchService: SearchService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.setupUser();
    console.log(this.cottageOwnerId);
    this.getAllCottagesByCottageOwnerId(this.cottageOwnerId);

    this.validateForm = this.fb.group({
      name: [""],
      type: [""]
    });
  }

  private setupUser(): void {
    this.user = JSON.parse(localStorage.getItem('user')!);
    this.cottageOwnerId = this.user.id;
  } 

  private getAllCottagesByCottageOwnerId(id): void {
    this.cottageService.getAllCottagesByCottageOwnerId(id).subscribe(data => {
      this.ownCottages = data;
      console.log(data);
    }, error => {
      alert("Error");
    })
  }

  public clientProfile(id) : void {

  }


  search():void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }
    let body = {
      name: this.validateForm.value.name == null ? "" : this.validateForm.value.name,
      ownerId: this.cottageOwnerId
    }
    this.searchService.searchOwnersCottages(body).subscribe(data => {
     this.ownCottages = data.cottages;
    }, error => {
    
    })
  }

  resetForm(): void {
    this.validateForm.reset();
    this.getAllCottagesByCottageOwnerId(this.cottageOwnerId);
  }

  public createAvailableTerm(id): void {
    this.router.navigateByUrl(`home-page/new-items/new-cottage-reservation/${this.user.id}`);
  }

  public createQuickReservation(id): void {
    this.router.navigateByUrl(`home-page/new-items/new-cottage-quick-reservation/${this.user.id}`);
  }

  change(id): void {
    this.router.navigateByUrl(`home-page/updates/update-cottage/${id}`);
  }

  add(): void {
    this.router.navigateByUrl(`home-page/new-items/cottage`);
  }

  delete(id): void {
    this.cottageService.deleteCottage(id).subscribe(data => {
      location.reload();
     }, error => {
      this.alertDelete = true;
     })
  }
}
