import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-requested-deletions',
  templateUrl: './requested-deletions.component.html',
  styleUrls: ['./requested-deletions.component.css']
})
export class RequestedDeletionsComponent implements OnInit {

  public user: any;
  public cottageOwnerId: any;
  public users = [];
  validateForm!: FormGroup;

  constructor(private userService: UserService, private router: Router,  private fb: FormBuilder) { }

  ngOnInit(): void {
    this.setupUser();
    this.getAllUserWithDeletionRequested();

    this.validateForm = this.fb.group({
      firstName: [""],
      lastName: [""],
      reasonForDeletion: [""]
    });
  }

  private setupUser(): void {
    this.user = JSON.parse(localStorage.getItem('user')!);
    this.cottageOwnerId = this.user.id;
  } 

  public approve(id): void {
    this.router.navigateByUrl(`home-page/updates/approve-deletion-request/${id}`);
  } 

  public deny(id): void {
    this.router.navigateByUrl(`home-page/updates/deny-deletion-request/${id}`);
  } 

  private getAllUserWithDeletionRequested(): void {
    this.userService.getAllUserWithDeletionRequested().subscribe(data => {
      this.users = data;
      console.log(data);
    }, error => {
      alert("Error");
    })
  }
}
