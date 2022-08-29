import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BoatReservationService {

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
    return this.http.post(this.baseURL + "api/boatReservations/createReservation", data, this.getAuthoHeader());
  }

  getByClientId(id: any){
    return this.http.get(this.baseURL + "api/boatReservations/clientReservations/"+ id, this.getAuthoHeader());
  }
}
