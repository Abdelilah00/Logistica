import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';


import {ActivatedRoute, Router} from '@angular/router';
import {first} from 'rxjs/operators';

import {environment} from '../../../../environments/environment';
import {AuthService} from '../../../core/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  submitted = false;
  error = '';
  returnUrl: string;

  // set the currenr year
  year: number = new Date().getFullYear();

  // tslint:disable-next-line: max-line-length
  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private service: AuthService,
              private authService: AuthService) {
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.loginForm.controls;
  }

  ngOnInit() {
    this.authService.logout();

    document.body.removeAttribute('data-layout');
    document.body.classList.add('auth-body-bg');

    this.loginForm = this.formBuilder.group({
      userName: ['string', [Validators.required]],
      password: ['string', [Validators.required]],
    });

    // reset login status
    // get return url from route parameters or default to '/'
    // tslint:disable-next-line: no-string-literal
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  /**
   * Form submit
   */
  onSubmit() {
    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    } else {
      this.service.signIn(this.loginForm.value).subscribe(resp => {
        this.submitted = true;
        this.authService.setToken(resp.token);
        this.router.navigate(['/dashboards/analytics']);
      });
    }
  }

}
