import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserSettingsService {
  private baseUrl = 'http://localhost:8080/api/v1/user/setting';

  constructor(private http: HttpClient) { }


  updateUserSettings(data:any,email:any): Observable<any> {
    const url = `${this.baseUrl}/${email}`;
    return this.http.post<any>(url, data,{
      headers:new HttpHeaders().set('Content-Type','application/json')
    });
  }
}