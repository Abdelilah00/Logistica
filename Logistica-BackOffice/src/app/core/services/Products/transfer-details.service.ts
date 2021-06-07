import {Injectable} from '@angular/core';
import {BaseService} from '../base-service.service';
import {Service, InputDetail, TransferDetails} from '../../models/all.models';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {retry} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TransferDetailsService extends BaseService<Service, Service> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'transferDetails');
  }

  getByTransferId(id: number): Observable<Array<TransferDetails>> {
    this.loading = true;
    return this.httpClient.get<Array<TransferDetails>>(`${this.baseUrl}/getByTransferId/${encodeURIComponent(String(id))}`)
      .pipe(retry(1));
  }
}
