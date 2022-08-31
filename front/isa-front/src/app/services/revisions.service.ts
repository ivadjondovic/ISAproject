import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RevisionsService {
  
  baseURL = "http://localhost:8080/";

  constructor(private http: HttpClient) { }

  getAuthoHeader() : any {
    const headers = {
      'Content-Type': 'application/json',
      'Authorization' : 'Bearer ' + localStorage.getItem("token")
    }
    return{
      headers: headers
    };
  } 

  getWaitingRevisions(){
    return this.http.get(this.baseURL + "api/revisions/waiting", this.getAuthoHeader());
  }

  approve(data: any){
    return this.http.put(this.baseURL + "api/revisions/approve", data, this.getAuthoHeader());
  }

  disapprove(data: any){
    return this.http.put(this.baseURL + "api/revisions/disapprove", data, this.getAuthoHeader());
  }
}
