import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register-service-provider',
  templateUrl: './register-service-provider.component.html',
  styleUrls: ['./register-service-provider.component.css']
})
export class RegisterServiceProviderComponent implements OnInit {

 
  errorRegister: boolean = false;
  validateForm!: FormGroup;
 
  constructor(private router:Router, private fb: FormBuilder, private authService: AuthService) {}

  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }

  const body = {
      username: this.validateForm.value.username,
      password: this.validateForm.value.password,
      rePassword: this.validateForm.value.rePassword,
      phoneNumber: this.validateForm.value.phoneNumber,
      address: this.validateForm.value.address,
      firstName: this.validateForm.value.firstName,
      lastName: this.validateForm.value.lastName,
      city: this.validateForm.value.city,
      country: this.validateForm.value.country,
      description: this.validateForm.value.description,
      registrationType: this.validateForm.value.registrationType
  }
  console.log(body);
  this.authService.registerServiceProvider(body).subscribe(data => {
    this.router.navigateByUrl(`frontpage/login`);
    
  }, error => {
    this.errorRegister = true;
  })
  }

  updateConfirmValidator(): void {
    /** wait for refresh value */
    Promise.resolve().then(() => this.validateForm.controls.checkPassword.updateValueAndValidity());
  }

  confirmationValidator = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value) {
      return { required: true };
    } else if (control.value !== this.validateForm.controls.password.value) {
      return { confirm: true, error: true };
    }
    return {};
  };

  getCaptcha(e: MouseEvent): void {
    e.preventDefault();
  }

  

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      username: [null, [Validators.email, Validators.required]],
      password: [null, [Validators.required]],
      rePassword: [null, [Validators.required, this.confirmationValidator]],
      firstName: [null, [Validators.required]],
      lastName: [null, [Validators.required]],
      address: [null, [Validators.required]],
      phoneNumber: [null, [Validators.required]],
      city: [null, [Validators.required]],
      country:  [null, [Validators.required]],
      description: [null, [Validators.required]],
      registrationType:  [null, [Validators.required]]
    });
  }

}
