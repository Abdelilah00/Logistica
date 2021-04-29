import {Observable} from 'rxjs';
import {BaseModel, IListModel} from '../models/base-model.model';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map, retry} from 'rxjs/operators';
import {environment} from '../../../environments/environment';


/**
 * parent service
 */
export abstract class BaseService<TModel extends BaseModel, TCreateModel extends BaseModel> {
  protected baseUrl: string;
  protected loading: boolean;

  protected httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'}),
  };

  protected constructor(protected httpClient: HttpClient, protected uri: string) {
    this.baseUrl = `${environment.apiBaseUrl}/${encodeURIComponent(uri)}`;
  }

  /**
   * GetAll Items List Options
   * @param skipCount (optional)
   * @param maxResultCount (optional)
   */
  getList(skipCount?: number | null | undefined, maxResultCount?: number | null | undefined): Observable<Array<TModel>> {
    this.loading = true;
    return this.httpClient.get<IListModel<TModel>>(this.baseUrl)
      .pipe(map(list => list.items), retry(1));
  }

  /**
   * GetAll Items Grid
   * @param skipCount (optional)
   * @param maxResultCount (optional)
   */
  getAll(skipCount?: number | null | undefined, maxResultCount?: number | null | undefined): Observable<Array<TModel>> {
    this.loading = true;
    return this.httpClient.get<Array<TModel>>(this.baseUrl)
      .pipe(retry(1));
  }

  /**
   * Get Item by uniqueIdentifier
   * @param id uniqueIdentifier of item
   */
  get(id: number): Observable<TModel> {
    return this.httpClient.get<TModel>(`${this.baseUrl}/${encodeURIComponent(String(id))}`)
      .pipe(retry(1));
  }

  /**
   * Create new Item
   * @param input create model of Item
   */
  create(input: TCreateModel): Observable<TModel> {
    console.log('create');
    return this.httpClient.post<TModel>(this.baseUrl, JSON.stringify(input), this.httpOptions)
      .pipe(retry(1));
  }

  /**
   * Update old Item
   * @param input update model of Item
   */
  update(input: TCreateModel): Observable<TModel> {
    return this.httpClient.put<TModel>(`${this.baseUrl}/${encodeURIComponent(String(input.id))}`, JSON.stringify(input), this.httpOptions)
      .pipe(retry(1));
  }

  /**
   * Delete Item by uniqueIdentifier
   * @param id uniqueIdentifier of item
   */
  delete(id: number): Observable<TModel> {
    return this.httpClient.delete<TModel>(`${this.baseUrl}/${encodeURIComponent(String(id))}`)
      .pipe(retry(1));
  }
}
