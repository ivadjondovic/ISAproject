import { CottageOwnerService } from './../../services/cottage-owner.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-update-cottage-owner',
  templateUrl: './update-cottage-owner.component.html',
  styleUrls: ['./update-cottage-owner.component.css']
})
export class UpdateCottageOwnerComponent implements OnInit {

  
  private id : any;
  validateForm: FormGroup
  public user: any;
  constructor(private route: ActivatedRoute, private fb: FormBuilder, private router: Router, private cottageOwnerService : CottageOwnerService) { }

  ngOnInit(): void {
    this.setupUser();
    this.setupForm();
    this.getDetails();
  }

  private setupUser(): void {
    this.user = JSON.parse(localStorage.getItem('user')!);
  } 

  public setupForm(): void {
    this.validateForm = this.fb.group({
      firstName: [null, [Validators.required]],
      lastName: [null, [Validators.required]],
      phoneNumber: [null, [Validators.required]],
      address: [null, [Validators.required]],
      city: [null, [Validators.required]],
      country: [null, [Validators.required]],
    });
  }

  public getDetails(): void {
    this.id = this.route.snapshot.params.id;
    console.log(this.id);
    this.cottageOwnerService.getCottageOwnerById(this.user.id).subscribe(data =>{
      console.log(data);
      const formValues = {
        firstName: data.firstName,
        lastName: data.lastName,
        phoneNumber: data.phoneNumber,
        address: data.address,
        city: data.city,
        country: data.country
      }
      this.validateForm.setValue(formValues);
    })
  }

  submitForm(): void {
    this.cottageOwnerService.updateCottageOwner(this.user.id, this.validateForm.value).subscribe(data => {
      this.router.navigateByUrl(`home-page`);
    }, error => {
      alert('Error');
    })
  }

}
