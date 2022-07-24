import { BoatService } from './../../services/boat.service';
import { Component, OnInit } from '@angular/core';
import { SearchService } from './../../services/search.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-own-boats',
  templateUrl: './own-boats.component.html',
  styleUrls: ['./own-boats.component.css']
})
export class OwnBoatsComponent implements OnInit {

  public user: any;
  public boatOwnerId: any;
  public ownBoats = [];
  validateForm!: FormGroup;
  public alert = true;
  public alertSucc = false;
  public alertDelete = false;

  constructor(private boatService: BoatService, private router: Router, private searchService: SearchService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.setupUser();
    this.getAllBoatsByBoatOwnerId(this.boatOwnerId);

    this.validateForm = this.fb.group({
      name: [""],
      type: [""]
    });
  }

  private setupUser(): void {
    this.user = JSON.parse(localStorage.getItem('user')!);
    this.boatOwnerId = this.user.id;
  } 

  private getAllBoatsByBoatOwnerId(id): void {
    this.boatService.getAllBoatsByBoatOwnerId(id).subscribe(data => {
      this.ownBoats = data;
      console.log(data);
    }, error => {
      alert("Error");
    })
  }


  search():void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }
    let body = {
      name: this.validateForm.value.name == null ? "" : this.validateForm.value.name,
      ownerId: this.boatOwnerId
    }
    this.searchService.searchOwnersBoats(body).subscribe(data => {
     this.ownBoats = data.boats;
    }, error => {
    
    })
  }

  resetForm(): void {
    this.validateForm.reset();
    this.getAllBoatsByBoatOwnerId(this.boatOwnerId);
  }

  change(id): void {
    this.router.navigateByUrl(`home-page/updates/update-boat/${id}`);
  }

  add(): void {
    this.router.navigateByUrl(`home-page/new-items/boat`);
  }

  delete(id): void {
    this.boatService.deleteBoat(id).subscribe(data => {
      location.reload();
     }, error => {
      this.alertDelete = true;
     })
  }

}
