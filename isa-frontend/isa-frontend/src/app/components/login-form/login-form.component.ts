import { ClientService } from './../../services/client.service';
import { CottageOwnerService } from './../../services/cottage-owner.service';
import { BoatOwnerService } from './../../services/boat-owner.service';
import { FishingInstructorService } from './../../services/fishing-instructor.service';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {
  validateForm!: FormGroup;

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      username: [null, [Validators.required]],
      password: [null, [Validators.required]]
      });
      const id = this.route.snapshot.params['id'];
      const client = this.route.snapshot.params['client'];

      if(id != undefined){
        const body = {
          patientId: id
        }
        if(client === 'fi'){
          this.fishingInstructorService.activateRegistration(body).subscribe(() => {
            alert('Uspešno ste se registrovali!');
            this.router.navigateByUrl(`home-page/login`);
          },
          error => {
            alert("Error login");
          });
       } else if(client === 'bo'){
            this.boatOwnerService.activateRegistration(body).subscribe(() => {
              alert('Uspešno ste se registrovali!');
              this.router.navigateByUrl(`home-page/login`);
            },
            error => {
              alert("Error login");
            });
        }else if(client === 'co'){
          this.cottageOwnerService.activateRegistration(body).subscribe(() => {
            alert('Uspešno ste se registrovali!');
            this.router.navigateByUrl(`home-page/login`);
          },
          error => {
            alert("Error login");
          });
      }
      else if(client === 'c'){
        this.clientService.activateRegistration(body).subscribe(() => {
          alert('Uspešno ste se registrovali!');
          this.router.navigateByUrl(`home-page/login`);
        },
        error => {
          alert("Error login");
        });
    }
      }
        

  }
  

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router, private route: ActivatedRoute, 
    private fishingInstructorService: FishingInstructorService, private boatOwnerService: BoatOwnerService, 
    private cottageOwnerService: CottageOwnerService, private clientService: ClientService) {}

  

  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }
    const body = {
      username: this.validateForm.value.username,
      password: this.validateForm.value.password
    }

    this.authService.login(body).subscribe(data => {
      const user = data;
      localStorage.setItem('user', JSON.stringify(user));
      this.router.navigate(['home-page']);
      console.log(data);
    
    }, error => { 
    })

    
  }

}
