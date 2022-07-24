import { CottageService } from './../../services/cottage.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder,  FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cottage',
  templateUrl: './cottage.component.html',
  styleUrls: ['./cottage.component.css']
})
export class CottageComponent implements OnInit {

  validateForm!: FormGroup;
  public addCottage = false;
  public user: any;

  constructor(private router:Router,private fb: FormBuilder, private cottageService: CottageService) {}


  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }

  const body = {
    name: this.validateForm.value.name,
    address: this.validateForm.value.address,
    description: this.validateForm.value.description,
    rulesOfConduct: this.validateForm.value.rulesOfConduct,
    priceList: this.validateForm.value.priceList,
    otherInformation: this.validateForm.value.otherInformation,
    numberOfRooms: this.validateForm.value.numberOfRooms,
    numberOfBedsPerRoom: this.validateForm.value.numberOfBedsPerRoom,
    cottageOwnerId: this.user.id

  }
  this.cottageService.addCottage(body).subscribe(data => {
    this.addCottage = true;
    this.router.navigateByUrl(`home-page`);   
  }, error => {
  })
  }

  ngOnInit(): void {
    this.setupUser();
    this.validateForm = this.fb.group({
      name: [null, [ Validators.required]],
      address: [null, [Validators.required]],
      description: [null, [Validators.required]],
      rulesOfConduct: [null, [Validators.required]],
      priceList: [null, [Validators.required]],
      otherInformation: [null, [Validators.required]],
      numberOfRooms: [null, [Validators.required]],
      numberOfBedsPerRoom: [null, [Validators.required]]
    });
  }

  private setupUser(): void {
    this.user = JSON.parse(localStorage.getItem('user'));
  } 

}
