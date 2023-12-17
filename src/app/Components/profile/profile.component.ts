import {Component, OnInit} from '@angular/core';
import {Member} from "../../Model/Member";
import {DataSharingService} from "../../Service/DataSharingService";
import {NavBarComponent} from "../nav-bar/nav-bar.component";
import {FormsModule} from "@angular/forms";
import {NgIf} from "@angular/common";
import {MemberService} from "../../Service/MemberService";

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [
    NavBarComponent,
    FormsModule,
    NgIf
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})



export class ProfileComponent implements OnInit {

  //
  // memberData: Member | null = null;
  //
  // constructor(private dataSharingService: DataSharingService) {}
  //
  //
  // ngOnInit(): void {
  //   // Subscribe to the member data in the shared service
  //   this.dataSharingService.getMemberData().subscribe((data: Member | null) => {
  //     this.memberData = data;
  //   });
  //
  //
  //
  // }

  isToggle : boolean = false;

  memberData: Member | null = null;
  newName: string = '';
  newLastName: string = '';

  constructor(private dataSharingService: DataSharingService ,     private memberService: MemberService
  ) {}

  ngOnInit(): void {
    // Subscribe to the member data in the shared service
    this.dataSharingService.getMemberData().subscribe((data: Member | null) => {
      this.memberData = data;
      this.newName = data?.firstName || '';
      this.newLastName = data?.lastName || '';
    });
  }


  updateMember(): void {
    if (this.memberData) {
      this.memberData.firstName = this.newName;
      this.memberData.lastName = this.newLastName;
      this.memberService.updateMember(this.memberData).subscribe(updatedMember => {
        this.memberData = updatedMember; // Update the member data in the component
        this.dataSharingService.setMemberData(updatedMember); // Update the member data in the shared service
      });
    }
  }

}
