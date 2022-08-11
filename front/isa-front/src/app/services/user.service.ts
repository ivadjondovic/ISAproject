import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

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

  register(data: any){
    return this.http.post(this.baseURL + "api/users/register", data);
  }

  login(data: any){
    return this.http.post(this.baseURL + "api/users/login", data);
  }

  current(){
    return this.http.get(this.baseURL + "api/users/current", this.getAuthoHeader());
  }

}
