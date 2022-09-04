import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ClientReviewService {

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

  createReview(data: any){
    return this.http.post(this.baseURL + "api/clientReview/createReview" ,  data, this.getAuthoHeader());
  }

  acceptPenalty(data: any){
    return this.http.put(this.baseURL + "api/clientReview/acceptPenalty" , data, this.getAuthoHeader());
  }

  declinePenalty(data: any){
    return this.http.put(this.baseURL + "api/clientReview/declinePenalty" , data, this.getAuthoHeader());
  }

  notCheckedPenalties(){
    return this.http.get(this.baseURL + "api/clientReview/notCheckedPenalties" , this.getAuthoHeader());
  }

}
