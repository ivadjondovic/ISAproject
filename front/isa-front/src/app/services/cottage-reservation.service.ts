import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CottageReservationService {

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

  createReservation(data: any){
    return this.http.post(this.baseURL + "api/cottageReservations/createReservation", data, this.getAuthoHeader());
  }

  getByClientId(id: any){
    return this.http.get(this.baseURL + "api/cottageReservations/clientReservations/"+ id, this.getAuthoHeader());
  }

  sort(data: any){
    return this.http.post(this.baseURL + "api/cottageReservations/sort" ,  data, this.getAuthoHeader());
  }



}
