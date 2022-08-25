import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FishingLessonService {

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

  createFishingLesson(data: any){
    return this.http.post(this.baseURL + "api/fishing/createFishingLesson", data, this.getAuthoHeader());
  }

  getByInstructorId(id: any){
    return this.http.get(this.baseURL + "api/fishing/fishingLessons/" + id, this.getAuthoHeader());
  }

  getById(id: any){
    return this.http.get(this.baseURL + "api/fishing/fishingLesson/" + id, this.getAuthoHeader());
  }

  editFishingLesson(data: any){
    return this.http.put(this.baseURL + "api/fishing/editFishingLesson", data, this.getAuthoHeader());
  }

}
