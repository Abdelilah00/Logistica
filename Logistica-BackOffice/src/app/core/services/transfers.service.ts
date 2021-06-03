import {BaseService} from './base-service.service';
import {Transfer} from '../models/all.models';
import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TransfersService extends BaseService<Transfer, Transfer> {

  constructor(httpClient: HttpClient) {
    super(httpClient, 'transfers');
  }

  /*  getByProductId(id: number): Observable<Array<Stock>> {
      this.loading = true;
      return this.httpClient.get<Array<Stock>>(`${this.baseUrl}/getByProductId/${encodeURIComponent(String(id))}`).pipe(retry(1));
    }*/
}
