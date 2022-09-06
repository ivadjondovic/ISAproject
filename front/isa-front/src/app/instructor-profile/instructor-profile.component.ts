import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PasswordDialogComponent } from '../password-dialog/password-dialog.component';
import { IncomeService } from '../services/income.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-instructor-profile',
  templateUrl: './instructor-profile.component.html',
  styleUrls: ['./instructor-profile.component.css']
})
export class InstructorProfileComponent implements OnInit {

  sChangePasswordClicked = false;
  user: any
  country: string
  oldPassword: string
  password: string
  newPassword = ""
  username: string
  incomeBetweenShow = false
  dateFrom = ""
  dateTo = ""
  reservationIncomeBetweenList: any[]
  reservationIncomeBetween: any[]

  constructor(public service: UserService, public dialog: MatDialog, public incomeService: IncomeService) { }

  ngOnInit(): void {
    this.service.current().subscribe((response: any) => {
      this.user = response;
      console.log(this.user.firstPasswordChanged)
      this.password = this.user.password;
    })
  }

  openDialog(enterAnimationDuration: string, exitAnimationDuration: string, username: string): void {
    this.username = username;
    const dialogRef = this.dialog.open(PasswordDialogComponent, {
      width: '40%',
      data: {username: this.username}
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.username = result;
    });
  }
  
  edit(){
    console.log(this.password)
    console.log(this.user.country)
    console.log(this.newPassword)
    let data = {
      username: this.user.username,
      password: this.newPassword,
      name: this.user.name,
      surname: this.user.surname,
      address: this.user.address,
      city: this.user.city,
      country: this.user.country,
      phoneNumber: this.user.phoneNumber
    }
    this.service.edit(data).subscribe((response: any) => {
      console.log(response);
      location.reload();

    })
    
  }

  showIncomeBetween() {
    this.incomeBetweenShow = true;
    let data = {
      adminId: this.user.id,
      dateFrom: this.dateFrom,
      dateTo: this.dateTo
    }

    this.incomeService.instructorIncomeBetween(data).subscribe((response: any) => {
      this.reservationIncomeBetween = response;
      this.corectDate1();
    })
  }

  corectDate1() {

    this.reservationIncomeBetweenList = [];
    for (let r of this.reservationIncomeBetween) {
      let startDate = new Date(r.startDate[0], r.startDate[1] - 1, r.startDate[2], r.startDate[3], r.startDate[4]);
      let endDate = new Date(r.endDate[0], r.endDate[1] - 1, r.endDate[2], r.endDate[3], r.endDate[4]);
      let price = r.price;
      let entityName = r.entityName
      let id = r.id;
      let type = r.type;
      let income = r.income;

      let data = {
        id: id,
        startDate: startDate,
        endDate: endDate,
        price: price,
        entityName: entityName,
        type: type,
        income: income
      }
      this.reservationIncomeBetweenList.push(data);
      console.log(startDate)

    }
  }

}
