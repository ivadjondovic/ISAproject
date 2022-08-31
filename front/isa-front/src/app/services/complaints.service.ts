import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ComplaintsService {

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

  getNotAnsweredComplaints(){
    return this.http.get(this.baseURL + "api/complaints/notAnswered", this.getAuthoHeader());
  }

  answer(data: any){
    return this.http.put(this.baseURL + "api/complaints/answer", data, this.getAuthoHeader());
  }
}
