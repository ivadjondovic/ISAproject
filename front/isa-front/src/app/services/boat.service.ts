import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BoatService {

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
  
  getAll(){
    return this.http.get(this.baseURL + "api/boat/boats");
  }

  getById(id: any){
    return this.http.get(this.baseURL + "api/boat/boat/" + id);
  }

  search(searchTerm: any){
    return this.http.get(this.baseURL + "api/boat/search/" + searchTerm);
  }
}
