import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DeleteAccountService } from './delete-account.service';

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

  notActivatedUsers(){
    return this.http.get(this.baseURL + "api/users/notActivatedUsers", this.getAuthoHeader());
  }

  acceptActivation(data: any){
    return this.http.put(this.baseURL + "api/users/acceptActivation", data, this.getAuthoHeader());
  }

  declineActivation(data: any){
    return this.http.put(this.baseURL + "api/users/declineActivation", data, this.getAuthoHeader());
  }

  editClient(data: any){
    return this.http.put(this.baseURL + "api/users/editClient", data, this.getAuthoHeader());
  }

  changePassword(data: any){
    return this.http.put(this.baseURL + "api/users/changePassword", data, this.getAuthoHeader());
  }

  edit(data: any){
    return this.http.put(this.baseURL + "api/users/edit", data, this.getAuthoHeader());
  }

  declineDeactivation(data: any){
    return this.http.put(this.baseURL + "api/users/declineDeactivation", data, this.getAuthoHeader());
  }

  acceptDeactivation(data: any){
    return this.http.put(this.baseURL + "api/users/acceptDeactivation", data, this.getAuthoHeader());
  }

  getClient(id: any){
    return this.http.get(this.baseURL + "api/users/getClient/" + id, this.getAuthoHeader());
  }

  getClients(){
    return this.http.get(this.baseURL + "api/users/getClients", this.getAuthoHeader());
  }

  getUsers(){
    return this.http.get(this.baseURL + "api/users/users", this.getAuthoHeader());
  }

  deleteUser(id: any) {
    return this.http.get(this.baseURL + "api/users/deleteUser/" + id, this.getAuthoHeader());
  }

}
