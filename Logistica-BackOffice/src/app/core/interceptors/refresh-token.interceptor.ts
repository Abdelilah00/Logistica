import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {AuthService} from '../services/auth/auth.service';
import {BehaviorSubject, Observable, Subject} from 'rxjs';
import {filter, switchMap, take} from 'rxjs/operators';
import {JwtService} from '../services/jwt/jwt.service';

@Injectable()
export class RefreshTokenInterceptor implements HttpInterceptor {
  private refreshTokenInProgress = false;
  // Refresh Token Subject tracks the current token, or is null if no token is currently
  // available (e.g. refresh pending).
  private refreshTokenSubject: Subject<any> = new BehaviorSubject<any>(
    null
  );

  constructor(private _authService: AuthService, private _jwtService: JwtService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (request.url.indexOf('OAuth') !== -1) {
      return next.handle(request);
    }

    const accessExpired = this._jwtService.tokenAccessIsExpired();

    if (accessExpired) {
      if (!this.refreshTokenInProgress) {
        this.refreshTokenInProgress = true;
        this.refreshTokenSubject.next(null);
        return this._authService.refreshLogin().pipe(
          switchMap((authResponse) => {
            this.refreshTokenInProgress = false;
            this.refreshTokenSubject.next(authResponse.refresh_token);
            return next.handle(this.injectToken(request));
          })
        );
      } else {
        return this.refreshTokenSubject.pipe(
          filter(result => result !== null),
          take(1),
          switchMap(() => {
            return next.handle(this.injectToken(request));
          })
        );
      }
    }
    return next.handle(this.injectToken(request));
  }

  private injectToken(request: HttpRequest<any>): any {
    // Get access token from Local Storage
    const currenttoken = this._jwtService.currentUserTokenValue;

    // If access token is null this means that user is not logged in
    // And we return the original request
    if (!currenttoken || !currenttoken.access_token) {
      return request;
    }

    // We clone the request, because the original request is immutable
    return request.clone({
      setHeaders: {
        Authorization: `Bearer ${currenttoken.access_token}`
      }
    });
  }
}
