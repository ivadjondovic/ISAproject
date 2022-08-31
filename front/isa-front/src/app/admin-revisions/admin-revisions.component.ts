import { Component, OnInit } from '@angular/core';
import { RevisionsService } from '../services/revisions.service';

@Component({
  selector: 'app-admin-revisions',
  templateUrl: './admin-revisions.component.html',
  styleUrls: ['./admin-revisions.component.css']
})
export class AdminRevisionsComponent implements OnInit {

  revisions: any[]
  revisionList: any[]
  constructor(public service: RevisionsService) { }

  ngOnInit(): void {

    this.service.getWaitingRevisions().subscribe((response: any) => {
      this.revisions = response;
      this.corectDate();
    })
  }

  corectDate() {

    this.revisionList = [];
    for (let r of this.revisions) {
      let date = new Date(r.date[0], r.date[1] - 1, r.date[2], r.date[3], r.date[4]);
      let description = r.description;
      let entityRate = r.entityRate;
      let id = r.id;
      let ownerRate = r.ownerRate;
      let entityName = r.entityName
      let revisionType = r.revisionType

      let data = {
        id: id,
        description: description,
        date: date,
        entityName: entityName,
        entityRate: entityRate,
        ownerRate: ownerRate,
        revisionType: revisionType


      }
      this.revisionList.push(data);

    }

  }

  approve(id: any, type: any){
    let data = {
      id: id,
      type: type
    }
    this.service.approve(data).subscribe((response: any) => {
      console.log(response)
      this.service.getWaitingRevisions().subscribe((response: any) => {
        this.revisions = response;
        this.corectDate();
      })
    })
  }
  disapprove(id: any, type: any){
    let data = {
      id: id,
      type: type
    }
    this.service.disapprove(data).subscribe((response: any) => {
      console.log(response)
      this.service.getWaitingRevisions().subscribe((response: any) => {
        this.revisions = response;
        this.corectDate();
      })
    })
  }

}
