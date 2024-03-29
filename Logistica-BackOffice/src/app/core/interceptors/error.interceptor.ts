import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {MatSnackBar} from '@angular/material/snack-bar';
import {Location} from '@angular/common';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private matSnackBar: MatSnackBar,
              private location: Location) {

  }


  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(catchError(error => {
      console.log(error);
      let errorMessage = '';
      if (error instanceof ErrorEvent) {
        // Get client-side error
        errorMessage = error.message;
      } else if (error.status === 0) {
        errorMessage = 'The server is not available';
      } else if (error.status === 403) {
        if (error.error.message === 'Access Denied') {
          this.matSnackBar.open('Verify your Username / password and try again', 'Ok', {
            verticalPosition: 'top',
            duration: 3000,
            panelClass: ['red-snackbar']
          });
        } else {
          console.log(error.error.message);
          this.matSnackBar.open(error.error.message, 'Ok', {
            verticalPosition: 'top',
            duration: 3000,
            panelClass: ['red-snackbar']
          });
        }
      } else if (error.status === 428) {
        console.log(error.error.message);
        this.matSnackBar.open(error.error.message, 'Ok', {
          verticalPosition: 'top',
          duration: 5000,
          panelClass: ['red-snackbar']
        });
      } else {
        console.log(error.error.message);
      }
      return throwError(errorMessage);
    }));
  }
}
