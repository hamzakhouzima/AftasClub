import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Member } from '../Model/Member';

@Injectable()
export class DataSharingService {
  private memberData = new BehaviorSubject<Member | null>(null);

  setMemberData(data: Member): void {
    this.memberData.next(data);
  }

  getMemberData(): BehaviorSubject<Member | null> {
    return this.memberData;
  }
}
