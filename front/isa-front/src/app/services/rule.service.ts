import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RuleService {
  
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

  delete(ruleId: any, lessonId: any){
    return this.http.delete(this.baseURL + "api/rules/delete/" + ruleId + "/" + lessonId, this.getAuthoHeader());
  }

  save(data: any) {
    return this.http.put(this.baseURL + "api/rules/save", data, this.getAuthoHeader())
  }

  add(data: any) {
    return this.http.post(this.baseURL + "api/rules/add", data, this.getAuthoHeader())
  }
}
