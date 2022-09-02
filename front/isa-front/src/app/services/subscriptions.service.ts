import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionsService {
  
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

  getClientSubscriptions(id: any){
    return this.http.get(this.baseURL + "api/subscriptions/clientSubscriptions/" + id, this.getAuthoHeader());
  }

  unsubscribeEntity(data: any){
    return this.http.put(this.baseURL + "api/subscriptions/unsubscribe", data, this.getAuthoHeader());
  }

}
