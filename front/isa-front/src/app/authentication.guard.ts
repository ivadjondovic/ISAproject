import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate} from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class AuthenticationGuard implements CanActivate {

  userRole: any;

  
  canActivate(route: ActivatedRouteSnapshot){
    if(route.data['role'] != this.getRole()) { 
       
      return false;
    }

    return true;
  }

  getRole() {

    let userStrng = localStorage.getItem('user');
    this.userRole = '';

    if(userStrng) {
      let user = JSON.parse(userStrng);
      console.log(user)
      this.userRole = user.userType;
      return this.userRole;
    }

    this.userRole = ''
    return this.userRole;
  }

}
