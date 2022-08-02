import { CottageComponent } from './../../new-items/cottage/cottage.component';
import { BoatService } from './../../services/boat.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-update-boat',
  templateUrl: './update-boat.component.html',
  styleUrls: ['./update-boat.component.css']
})
export class UpdateBoatComponent implements OnInit {

  
  private id : any;
  validateForm: FormGroup
  public user: any;
  public alertDelete = false;  
  public alert = true;
  public cancellationReservationFee : any;

  constructor(private boatService: BoatService, private route: ActivatedRoute, private fb: FormBuilder, private router: Router) { }

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
      type: [null, [Validators.required]],
      address: [null, [Validators.required]],
      description: [null, [Validators.required]],
      rulesOfConduct: [null, [Validators.required]],
      priceList: [null, [Validators.required]],
      length: [null, [Validators.required]],
      engineNumber: [null, [Validators.required]],
      enginePower: [null, [Validators.required]],
      maxSpeed: [null, [Validators.required]],
      capacity: [null, [Validators.required]],
      fishingEquipment: [null, [Validators.required]],
      cancellationReservationFee: [null, [Validators.required]]
    });
  }

  public getDetails(): void {
    this.id = this.route.snapshot.params.id;
    this.boatService.getBoat(this.id).subscribe(data =>{
  
      const formValues = {
        name: data.name,
        type: data.type,
        address: data.address,
        description: data.description,
        rulesOfConduct: data.rulesOfConduct,
        priceList: data.priceList,
        length: data.length,
        engineNumber: data.engineNumber,
        enginePower: data.enginePower,
        maxSpeed: data.maxSpeed,
        capacity: data.capacity,
        fishingEquipment: data.fishingEquipment,
        cancellationReservationFee: data.cancellationReservationFee
        
      }
      console.log(data);
      this.validateForm.setValue(formValues);
    })
  }

  submitForm(): void {
    let body = {
      name:  this.validateForm.value.name,
      type: this.validateForm.value.type,
      address:  this.validateForm.value.address,
      description:  this.validateForm.value.description,
      rulesOfConduct:  this.validateForm.value.rulesOfConduct,
      priceList:  this.validateForm.value.priceList,
      length:  this.validateForm.value.length,
      engineNumber:  this.validateForm.value.engineNumber,
      enginePower:  this.validateForm.value.enginePower,
      maxSpeed:  this.validateForm.value.maxSpeed,
      capacity:  this.validateForm.value.capacity,
      fishingEquipment:  this.validateForm.value.fishingEquipment,
      cancellationReservationFee: this.validateForm.value.cancellationReservationFee
    }
    this.boatService.updateBoat(this.id, body).subscribe(data => {
      this.router.navigateByUrl(`home-page`);
    }, error => {
      this.alertDelete = true;
    })
  }

}
