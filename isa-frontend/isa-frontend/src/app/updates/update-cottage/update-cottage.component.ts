import { CottageService } from './../../services/cottage.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-update-cottage',
  templateUrl: './update-cottage.component.html',
  styleUrls: ['./update-cottage.component.css']
})
export class UpdateCottageComponent implements OnInit {

  private id : any;
  validateForm: FormGroup
  public user: any;
  public alertDelete = false;  
  public alert = true;

  constructor(private cottageService: CottageService, private route: ActivatedRoute, private fb: FormBuilder, private router: Router) { }

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
      name: [null, [Validators.required]],
      address: [null, [Validators.required]],
      description: [null, [Validators.required]],
      rulesOfConduct: [null, [Validators.required]],
      priceList: [null, [Validators.required]],
      numberOfRooms: [null, [Validators.required]],
      numberOfBedsPerRoom: [null, [Validators.required]],
      otherInformation: [null, [Validators.required]]
    });
  }

  public getDetails(): void {
    this.id = this.route.snapshot.params.id;
    this.cottageService.getCottage(this.id).subscribe(data =>{
      const formValues = {
        name: data.name,
        address: data.address,
        description: data.description,
        rulesOfConduct: data.rulesOfConduct,
        priceList: data.priceList,
        numberOfRooms: data.numberOfRooms,
        numberOfBedsPerRoom: data.numberOfBedsPerRoom,
        otherInformation: data.otherInformation
      }
      this.validateForm.setValue(formValues);
    })
  }

  submitForm(): void {
    this.cottageService.updateCottage(this.id, this.validateForm.value).subscribe(data => {
      this.router.navigateByUrl(`home-page`);
    }, error => {
      this.alertDelete = true;
    })
  }

}
