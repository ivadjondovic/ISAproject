import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { MatDialog, MatDialogRef} from '@angular/material/dialog';
import { DialogOverviewComponent } from '../dialog-overview/dialog-overview.component';


@Component({
  selector: 'app-registration-approval',
  templateUrl: './registration-approval.component.html',
  styleUrls: ['./registration-approval.component.css']
})
export class RegistrationApprovalComponent implements OnInit {

  notActivatedUsers: any[]
  username: string
  user: any

  constructor(public service : UserService, public dialog: MatDialog) { }

  openDialog(enterAnimationDuration: string, exitAnimationDuration: string, username: string): void {
    this.username = username;
    const dialogRef = this.dialog.open(DialogOverviewComponent, {
      width: '50%',
      data: {username: this.username}
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.username = result;
    });
  }


  ngOnInit(): void {

    this.service.notActivatedUsers().subscribe((response: any) => {
      this.notActivatedUsers = response;
      console.log(response);
    })

  }

  acceptActivation(username: string) {
    let data = {
      username : username,
      reason: ''
    }

    this.service.acceptActivation(data).subscribe((response: any) => {
      this.user = response;
      console.log(response);
      location.reload();
    })
  }

}

