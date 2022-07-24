import { Injectable } from '@angular/core';
import { HttpClient  } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BoatService {

  constructor(private http: HttpClient) { }

  public deleteBoat(id): Observable<any> {
    return this.http.delete(`http://localhost:8080/boats/${id}`);
  }

  public getAllBoatsByBoatOwnerId(id): Observable<any> {
    return this.http.get(`http://localhost:8080/boats/${id}/boat-owner`);
  }

  public getBoat(id): Observable<any> {
    return this.http.get(`http://localhost:8080/boats/${id}`);
  }

  public updateBoat(id, body): Observable<any> {
    return this.http.put(`http://localhost:8080/boats/${id}/update`, body);
  }

  public addBoat(body): Observable<any> {
    return this.http.post(`http://localhost:8080/boats`, body);
  }
}
