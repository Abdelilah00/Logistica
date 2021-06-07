import {Injectable} from '@angular/core';
import {BaseService} from '../base-service.service';
import {InputDetail} from '../../models/all.models';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {retry} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class InputDetailsService extends BaseService<InputDetail, InputDetail> {
  constructor(httpClient: HttpClient) {
    super(httpClient, 'inputDetails');
  }

  getByInputId(id: number): Observable<Array<InputDetail>> {
    this.loading = true;
    return this.httpClient.get<Array<InputDetail>>(`${this.baseUrl}/getByInputId/${encodeURIComponent(String(id))}`)
      .pipe(retry(1));
  }
}
