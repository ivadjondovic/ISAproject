import { Component, OnInit } from '@angular/core';
import { ComplaintsService } from '../services/complaints.service';

@Component({
  selector: 'app-admin-complaints',
  templateUrl: './admin-complaints.component.html',
  styleUrls: ['./admin-complaints.component.css']
})
export class AdminComplaintsComponent implements OnInit {

  complaints: any[]
  complaintList: any[]
  constructor(public service: ComplaintsService) { }

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

      let data = {
        id: id,
        description: description,
        date: date,
        entityName: entityName,
        complaintType: complaintType


      }
      this.complaintList.push(data);

    }

  }

  answer(){}

}
