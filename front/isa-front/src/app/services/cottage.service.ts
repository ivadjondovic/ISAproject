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

  sortAvailable(data: any){
    return this.http.post(this.baseURL + "api/cottage/sortAvailable" ,  data, this.getAuthoHeader());
  }

  getAvailableCottages(data: any){
    return this.http.post(this.baseURL + "api/cottage/availableCottages" ,  data, this.getAuthoHeader());
  }

  getAvailableBoatsForCertainDate(data: any){
    return this.http.post(this.baseURL + "api/cottage/availableCottagesForCertainDate" ,  data, this.getAuthoHeader());
  }

  subscribedCottages(id: any){
    return this.http.get(this.baseURL + "api/cottage/subscribedCottages/" + id, this.getAuthoHeader());
  }

  searchByManyParams(data: any){
    return this.http.post(this.baseURL + "api/cottage/searchByManyParams" ,  data, this.getAuthoHeader());
  }

  deleteCottage(id: string){
    return this.http.get(this.baseURL + "api/cottage/delete/" + id, this.getAuthoHeader());
  }
  
}
