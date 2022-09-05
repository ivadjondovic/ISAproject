import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DeleteReasonDialogComponent } from '../delete-reason-dialog/delete-reason-dialog.component';
import { NotDeleteReasonDialogComponent } from '../not-delete-reason-dialog/not-delete-reason-dialog.component';
import { DeleteAccountService } from '../services/delete-account.service';

@Component({
  selector: 'app-delete-requests',
  templateUrl: './delete-requests.component.html',
  styleUrls: ['./delete-requests.component.css']
})
export class DeleteRequestsComponent implements OnInit {

  requests: any[]
  id: any
  constructor(public dialog: MatDialog, public service: DeleteAccountService, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.service.getAll().subscribe((response: any) => {
      this.requests = response;
    })
  }

  acceptRequest(id: string){

  }

  decline(enterAnimationDuration: string, exitAnimationDuration: string, id: string): void {
    this.id = id;
    const dialogRef = this.dialog.open(NotDeleteReasonDialogComponent, {
      width: '50%',
      data: {id: this.id}
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.id = result;
    });
  }

  accept(enterAnimationDuration: string, exitAnimationDuration: string, id: string): void {
    this.id = id;
    const dialogRef = this.dialog.open(DeleteReasonDialogComponent, {
      width: '50%',
      data: {id: this.id}
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.id = result;
    });
  }

}
