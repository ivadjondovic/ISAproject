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

  getByIdForInstructor(id: any){
    return this.http.get(this.baseURL + "api/fishing/instructorFishingLesson/" + id, this.getAuthoHeader());
  }

  editFishingLesson(data: any){
    return this.http.put(this.baseURL + "api/fishing/editFishingLesson", data, this.getAuthoHeader());
  }

  getAll(){
    return this.http.get(this.baseURL + "api/fishing/fishingLessons");
  }

  getOneLesson(id: any){
    return this.http.get(this.baseURL + "api/fishing/lessonById/" + id);
  }

  sort(data: any){
    return this.http.post(this.baseURL + "api/fishing/sort" ,  data);
  }

  sortAvailable(data: any){
    return this.http.post(this.baseURL + "api/fishing/sortAvailable" ,  data, this.getAuthoHeader());
  }

  search(searchTerm: any){
    return this.http.get(this.baseURL + "api/fishing/search/" + searchTerm);
  }

  searchForInstructor(searchTerm: any, id: any){
    return this.http.get(this.baseURL + "api/fishing/searchForInstructor/" + searchTerm + "/" + id, this.getAuthoHeader());
  }

  getAvailableLessons(data: any){
    return this.http.post(this.baseURL + "api/fishing/availableLessons" ,  data, this.getAuthoHeader());
  }

  getAvailableLessonsForInstructor(data: any){
    return this.http.post(this.baseURL + "api/fishing/availableLessonsForInstructor" ,  data, this.getAuthoHeader());
  }

  getAvailableBoatsForCertainDate(data: any){
    return this.http.post(this.baseURL + "api/fishing/availableLessonsForCertainDate" ,  data, this.getAuthoHeader());
  }

  subscribedLessons(id: any){
    return this.http.get(this.baseURL + "api/fishing/subscribedLessons/" + id, this.getAuthoHeader());
  }

  searchByManyParams(data: any){
    return this.http.post(this.baseURL + "api/fishing/searchByManyParams" ,  data, this.getAuthoHeader());
  }

  deleteLesson(id: any){
    return this.http.get(this.baseURL + "api/fishing/delete/" + id, this.getAuthoHeader());
  }

}
