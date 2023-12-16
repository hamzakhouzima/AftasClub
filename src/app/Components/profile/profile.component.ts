import {Component, OnInit} from '@angular/core';
import {Member} from "../../Model/Member";
import {DataSharingService} from "../../Service/DataSharingService";
import {NavBarComponent} from "../nav-bar/nav-bar.component";

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [
    NavBarComponent
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit {


  memberData: Member | null = null;

  constructor(private dataSharingService: DataSharingService) {}


  ngOnInit(): void {
    // Subscribe to the member data in the shared service
    this.dataSharingService.getMemberData().subscribe((data: Member | null) => {
      this.memberData = data;
    });
  }

}
