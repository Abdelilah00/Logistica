import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';

import {JwtService} from '../services/jwt/jwt.service';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  constructor(private _jwtService: JwtService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // add authorization header with jwt token if available
    const currenttoken = this._jwtService.currentUserTokenValue;
    if (currenttoken && currenttoken.access_token) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${currenttoken.access_token}`
        }
      });
    }
    return next.handle(request);
  }
}
