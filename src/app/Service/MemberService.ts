import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
// import { HttpClientModule } from '@angular/common/http';

import { Observable } from 'rxjs';
import { Member } from '../Model/Member'; // Import the Member interface

@Injectable({
  providedIn: 'root'
})
export class MemberService {
  private apiUrl = 'http://localhost:8080'; // Replace with your API URL
  searchResults: any;

  constructor(private http: HttpClient) { }

  // getMembers(): Observable<Member[]> {
  //   return this.http.get<Member[]>(`${this.apiUrl}member/searchMember/`);
  // }

  getMemberByIdentityNumber(identityNumber: string): Observable<Member> {
    console.log("identityNumber =>>" + identityNumber);
    return this.http.get<Member>(`${this.apiUrl}/searchMember/${identityNumber}`);
    // return this.http.get<Member>(`${this.apiUrl}/member/searchMember/hamza`);
  }

  addMember(member: Member): Observable<Member> {
    return this.http.post<Member>(`${this.apiUrl}members`, member);
  }

  updateMember(member: Member): Observable<Member> {
    return this.http.put<Member>(`${this.apiUrl}members/${member.id}`, member);
  }

  deleteMember(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}members/${id}`);
  }
}
