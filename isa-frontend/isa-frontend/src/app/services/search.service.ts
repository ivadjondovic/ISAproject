import { Injectable } from '@angular/core';
import { HttpClient,  HttpParams  } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) { }

  public searchOwnersCottages(data) : Observable<any>{
    let queryParams = {
      params : new HttpParams().set('name', data["name"])
                               .set('ownerId', data["ownerId"])
    } 
    return this.http.get(`http://localhost:8080/search/owners-cottages`, queryParams);
  }

  public searchOwnersBoats(data) : Observable<any>{
    let queryParams = {
      params : new HttpParams().set('name', data["name"])
                               .set('ownerId', data["ownerId"])
    } 
    return this.http.get(`http://localhost:8080/search/owners-boats`, queryParams);
  }

}
