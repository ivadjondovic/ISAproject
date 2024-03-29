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

  sort(data: any){
    return this.http.post(this.baseURL + "api/boat/sort" ,  data);
  }

  sortAvailable(data: any){
    return this.http.post(this.baseURL + "api/boat/sortAvailable" ,  data, this.getAuthoHeader());
  }

  getAvailableBoats(data: any){
    return this.http.post(this.baseURL + "api/boat/availableBoats" ,  data, this.getAuthoHeader());
  }

  getAvailableBoatsForCertainDate(data: any){
    return this.http.post(this.baseURL + "api/boat/availableBoatsForCertainDate" ,  data, this.getAuthoHeader());
  }

  subscribedBoats(id: any){
    return this.http.get(this.baseURL + "api/boat/subscribedBoats/" + id, this.getAuthoHeader());
  }

  searchByManyParams(data: any){
    return this.http.post(this.baseURL + "api/boat/searchByManyParams" ,  data, this.getAuthoHeader());
  }

  deleteBoat(id: any){
    return this.http.get(this.baseURL + "api/boat/delete/" + id, this.getAuthoHeader());
  }
  
}
