import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ReservationsService {

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

  getClientReservations(id: any){
    return this.http.get(this.baseURL + "api/reservations/notPassed/"+ id, this.getAuthoHeader());
  }

  cancelReservation(data: any){
    return this.http.put(this.baseURL + "api/reservations/cancel", data, this.getAuthoHeader());
  }

  adminIncome(id: any) {
    return this.http.get(this.baseURL + "api/reservations/adminIncome/" + id, this.getAuthoHeader());
  }

  adminIncomePercentage(data: any) {
    return this.http.put(this.baseURL + "api/reservations/adminIncomePercentage", data, this.getAuthoHeader());
  }

}
