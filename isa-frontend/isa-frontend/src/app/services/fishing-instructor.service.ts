import { Injectable } from '@angular/core';
import { HttpClient  } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FishingInstructorService {

  constructor(private http: HttpClient) { }

  public activateRegistration(body): Observable<any> {
    return this.http.put('http://localhost:8080/fishing-instructors/confirm', body);
  }
}
