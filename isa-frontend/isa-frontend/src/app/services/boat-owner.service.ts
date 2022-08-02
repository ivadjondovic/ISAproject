import { Injectable } from '@angular/core';
import { HttpClient  } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BoatOwnerService {

  constructor(private http: HttpClient) { }

  public changePasswordForBoatOwner(id, body): Observable<any> {
    return this.http.put(`http://localhost:8080/boat-owners/${id}/password`, body);
  }

  public updateBoatOwner(id, body): Observable<any> {
    return this.http.put(`http://localhost:8080/boat-owners/update/${id}`, body);
  }

  public getBoatOwnerById(id): Observable<any> {
    return this.http.get(`http://localhost:8080/boat-owners/${id}`);
  }

  public activateRegistration(body): Observable<any> {
    return this.http.put('http://localhost:8080/boat-owners/confirm', body);
  }

}
