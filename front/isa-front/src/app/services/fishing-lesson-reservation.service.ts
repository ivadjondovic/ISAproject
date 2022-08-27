import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FishingLessonReservationService {

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
    return this.http.post(this.baseURL + "api/fishingLessonReservations/createReservation", data, this.getAuthoHeader());
  }
}
