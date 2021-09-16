import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {PageableApiService} from '../app-security-module/services/pageable-api-service';
import {ChartInterval, ChartRange, IndicatorChartData, IndicatorDetails} from '../models/indicator';


const API_URL = '/api/indicators';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class IndicatorsApiService extends PageableApiService<IndicatorDetails> {

  constructor(
    http: HttpClient
  ) {
    super(http, API_URL);
  }

  getAllIndicators(): Observable<IndicatorDetails[]> {
    return this.http.get<IndicatorDetails[]>(API_URL, httpOptions);
  }

  getIndicator(indicatorId: string): Observable<IndicatorDetails> {
    return this.http.get<IndicatorDetails>(`${API_URL}/${indicatorId}`, httpOptions);
  }

  getIndicatorChartDates(indicatorIds: string[], interval?: ChartInterval, range?: ChartRange): Observable<IndicatorChartData[]> {
    let httpParams = new HttpParams();
    httpParams = interval ? httpParams.append('interval', interval) : httpParams;
    httpParams = range ? httpParams.append('range', range) : httpParams;
    indicatorIds.forEach(indicatorId => httpParams = httpParams.append('indicatorIds', indicatorId));
    return this.http.get<IndicatorChartData[]>(`${API_URL}/chart`, {headers: httpOptions.headers, params: httpParams});
  }

  getDefaultIndicators(): Observable<string[]> {
    return this.http.get<string[]>(`${API_URL}/defaults`, httpOptions);
  }
}
