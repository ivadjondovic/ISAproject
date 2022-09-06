import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class IncomeService {
  
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

  getReservationIncome(id: any){
    return this.http.get(this.baseURL + "api/income/getReservationIncome/" + id, this.getAuthoHeader());
  }

  reservationIncomeBetween(data: any){
    return this.http.post(this.baseURL + "api/income/getReservationIncomeBetween", data, this.getAuthoHeader());
  }

}
