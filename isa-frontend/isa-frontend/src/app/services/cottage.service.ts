import { Injectable } from '@angular/core';
import { HttpClient  } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CottageService {

  constructor(private http: HttpClient) { }

  public deleteCottage(id): Observable<any> {
    return this.http.delete(`http://localhost:8080/cottages/${id}`);
  }

  public getAllCottagesByCottageOwnerId(id): Observable<any> {
    return this.http.get(`http://localhost:8080/cottages/${id}/cottage-owner`);
  }

  public getCottage(id): Observable<any> {
    return this.http.get(`http://localhost:8080/cottages/${id}`);
  }

  public updateCottage(id, body): Observable<any> {
    return this.http.put(`http://localhost:8080/cottages/${id}/update`, body);
  }

  public addCottage(body): Observable<any> {
    return this.http.post(`http://localhost:8080/cottages`, body);
  }
}
