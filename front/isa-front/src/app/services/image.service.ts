import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

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

  delete(imageId: any, lessonId: any){
    return this.http.delete(this.baseURL + "api/images/delete/" + imageId + "/" + lessonId, this.getAuthoHeader());
  }

  add(data: any) {
    return this.http.post(this.baseURL + "api/images/add", data, this.getAuthoHeader())
  }
}
