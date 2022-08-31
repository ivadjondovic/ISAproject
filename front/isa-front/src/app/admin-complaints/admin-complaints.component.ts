import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AnswerComplaintDialogComponent } from '../answer-complaint-dialog/answer-complaint-dialog.component';
import { ComplaintsService } from '../services/complaints.service';

@Component({
  selector: 'app-admin-complaints',
  templateUrl: './admin-complaints.component.html',
  styleUrls: ['./admin-complaints.component.css']
})
export class AdminComplaintsComponent implements OnInit {

  id: any
  complaints: any[]
  complaintList: any[]
  constructor(public dialog: MatDialog, public service: ComplaintsService) { }

  ngOnInit(): void {
    this.service.getNotAnsweredComplaints().subscribe((response: any) => {
      this.complaints = response;
      this.corectDate();
    })
  }

  corectDate() {

    this.complaintList = [];
    for (let r of this.complaints) {
      let date = new Date(r.date[0], r.date[1] - 1, r.date[2], r.date[3], r.date[4]);
      let description = r.description;
      let id = r.id;
      let entityName = r.entityName
      let complaintType = r.complaintType
      let clientId = r.clientId

      let data = {
        id: id,
        description: description,
        date: date,
        entityName: entityName,
        complaintType: complaintType,
        clientId: clientId


      }
      this.complaintList.push(data);

    }

  }

  answer(enterAnimationDuration: string, exitAnimationDuration: string, id: any, type: any, clientId: any) {
    this.id = id;
    const dialogRef = this.dialog.open(AnswerComplaintDialogComponent, {
      width: '45%',
      data: {
        id: this.id,
        type: type,
        clientId: clientId
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.id = result;
      this.service.getNotAnsweredComplaints().subscribe((response: any) => {
        this.complaints = response;
        this.corectDate();
      })
    });
  }


}
