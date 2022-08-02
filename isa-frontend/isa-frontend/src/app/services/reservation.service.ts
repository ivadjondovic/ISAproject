import { Injectable } from '@angular/core';
import { HttpClient  } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http: HttpClient) { }

  public getAllReservationsByCottageOwnerId(id): Observable<any> {
    return this.http.get(`http://localhost:8080/reservation/${id}/cottage-owner`);
  }

  public getAllReservationsByBoatOwnerId(id): Observable<any> {
    return this.http.get(`http://localhost:8080/reservation/${id}/boat-owner`);
  }

  public createReservation(body): Observable<any> {
    return this.http.put(`http://localhost:8080/reservation/create`, body);
  }

  public createQuickReservation(body): Observable<any> {
    return this.http.put(`http://localhost:8080/quick-reservation/create`, body);
  }
}
