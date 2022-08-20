import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CottageService {

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
    return this.http.get(this.baseURL + "api/cottage/cottages");
  }

  getById(id: any){
    return this.http.get(this.baseURL + "api/cottage/cottage/" + id);
  }

  search(searchTerm: any){
    return this.http.get(this.baseURL + "api/cottage/search/" + searchTerm);
  }

  sort(data: any){
    return this.http.post(this.baseURL + "api/cottage/sort" ,  data);
  }
}
