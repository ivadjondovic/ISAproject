import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdditionalServiceService {

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

  delete(id: any){
    return this.http.delete(this.baseURL + "api/additionalServices/delete/" + id, this.getAuthoHeader());
  }

  save(data: any) {
    return this.http.put(this.baseURL + "api/additionalServices/save", data, this.getAuthoHeader())
  }

  add(data: any) {
    return this.http.post(this.baseURL + "api/additionalServices/add", data, this.getAuthoHeader())
  }
}
