import {Component, OnInit} from '@angular/core';
import {Member} from "../../Model/Member";
import {MemberService} from "../../Service/MemberService";
import {firstValueFrom} from "rxjs";
import {FormsModule} from "@angular/forms";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {DataSharingService} from "../../Service/DataSharingService";
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-bar',
  standalone: true,
  imports: [
    FormsModule,
    // DataSharingService

  ],
  templateUrl: './search-bar.component.html',
  styleUrl: './search-bar.component.css'
})

export class SearchBarComponent implements OnInit{

  public searchTerm: string = '';
  private foundMembers: Member = {} as Member;
  private DataSharingService: DataSharingService = new DataSharingService();
  // private router: any;

  constructor(private memberService: MemberService , private dataSharingService: DataSharingService ,   private router: Router
  ) {
  }
  ngOnInit() {
  }

  async searchMember(): Promise<any> {
    try {
      console.log("form submitted");
      this.foundMembers = await firstValueFrom(this.memberService.getMemberByIdentityNumber(this.searchTerm));
      console.log(this.searchTerm);
      console.log(this.foundMembers);
      this.dataSharingService.setMemberData(this.foundMembers);
      // this.router.navigate(['/member-details']);
      await this.router.navigate(['/member-details']);


      // this.DataSharingService.setFoundMembers(this.foundMembers);
    } catch (error) {
      console.error(error);
    }
  }
}
