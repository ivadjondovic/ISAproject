import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  baseURL = "http://localhost:8080/";

  constructor(private http: HttpClient) { }

  register(data: any){
    return this.http.post(this.baseURL + "api/users/register", data);
  }

}
