import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder,  FormGroup, Validators } from '@angular/forms';
import { Router,ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-request-for-deletion',
  templateUrl: './request-for-deletion.component.html',
  styleUrls: ['./request-for-deletion.component.css']
})
export class RequestForDeletionComponent implements OnInit {

  validateForm!: FormGroup;
  public user: any;
  private type : any;

  constructor(private route: ActivatedRoute, private router:Router,private fb: FormBuilder, private userService: UserService) {}


  submitForm(): void {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }
  this.type = this.route.snapshot.params.client;
  const body = {
    text: this.validateForm.value.text,
    id: this.user.id,
    type: this.type
  }
  this.userService.requestDeletion(body).subscribe(data => {
    this.router.navigateByUrl(`home-page`);   
  }, error => {
  })
  }

  ngOnInit(): void {
    this.setupUser();
    this.validateForm = this.fb.group({
      text: [null, [ Validators.required]]
    });
  }

  private setupUser(): void {
    this.user = JSON.parse(localStorage.getItem('user'));
  } 
}
