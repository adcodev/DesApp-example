import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { UserRequest, UserResponse } from '../models/user.model';
import { UserReportParams, UserReportResponse } from '../models/user-report.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private http = inject(HttpClient);
  private readonly baseUrl = `${environment.api}/api/user`;

  getUsers(): Observable<UserResponse[]> {
    return this.http.get<UserResponse[]>(`${this.baseUrl}/list`);
  }

  getUserById(id: string): Observable<UserResponse> {
    return this.http.get<UserResponse>(`${this.baseUrl}/${id}`);
  }

  registerUser(userData: UserRequest): Observable<UserResponse> {
    return this.http.post<UserResponse>(`${this.baseUrl}/register`, userData);
  }

  updateUser(id: string, userData: UserRequest): Observable<UserResponse> {
    return this.http.put<UserResponse>(`${this.baseUrl}/${id}`, userData);
  }

  deleteUser(id: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  getUserReport(params: UserReportParams = {}): Observable<UserReportResponse> {
    const query = new URLSearchParams();

    if (params.emailType) query.set('emailType', params.emailType);
    if (params.role) query.set('role', params.role);
    if (params.page !== undefined) query.set('page', String(params.page));
    if (params.size !== undefined) query.set('size', String(params.size));

    const queryString = query.toString();
    const url = `${this.baseUrl}/reports${queryString ? `?${queryString}` : ''}`;

    return this.http.get<UserReportResponse>(url);
  }
}
