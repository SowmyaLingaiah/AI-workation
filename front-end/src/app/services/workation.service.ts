/**
 * @author sowmya.lingaiah
 */
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Workation } from '../models/workation.model';

@Injectable({ providedIn: 'root' })
export class WorkationService {
  private apiUrl = 'http://localhost:8080/workflex/workation';

  constructor(private http: HttpClient) {}

  getWorkations(sortBy: string = 'workationId', sortDir: string = 'asc'): Observable<Workation[]> {
    const params = new HttpParams()
      .set('sortBy', sortBy)
      .set('sortDir', sortDir);
    return this.http.get<Workation[]>(this.apiUrl, { params });
  }
}
