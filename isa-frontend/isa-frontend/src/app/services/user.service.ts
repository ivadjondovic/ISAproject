import { Injectable } from '@angular/core';
import { HttpClient,  HttpParams  } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  public requestDeletion(body): Observable<any> {
    return this.http.post('http://localhost:8080/users/request-for-deletion', body);
  }

  public getAllUserWithDeletionRequested(): Observable<any> {
    return this.http.get('http://localhost:8080/users/deletion-requested');
  }

  public approveDeletionRequest(body): Observable<any> {
    return this.http.put('http://localhost:8080/users/approve-deletion-request', body);
  }

  public denyDeletionRequest(body): Observable<any> {
    return this.http.put('http://localhost:8080/users/deny-deletion-request', body);
  }

}
