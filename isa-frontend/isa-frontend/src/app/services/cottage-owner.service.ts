import { Injectable } from '@angular/core';
import { HttpClient  } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CottageOwnerService {

  constructor(private http: HttpClient) { }

  public changePasswordForCottageOwner(id, body): Observable<any> {
    return this.http.put(`http://localhost:8080/cottage-owners/${id}/password`, body);
  }

  public updateCottageOwner(id, body): Observable<any> {
    return this.http.put(`http://localhost:8080/cottage-owners/update/${id}`, body);
  }

  public getCottageOwnerById(id): Observable<any> {
    return this.http.get(`http://localhost:8080/cottage-owners/${id}`);
  }
}
