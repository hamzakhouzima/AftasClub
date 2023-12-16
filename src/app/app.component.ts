import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import {NavBarComponent} from "./Components/nav-bar/nav-bar.component";
// import {CardsComponent} from "./Components/cards/cards.component";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {DataSharingService} from "./Service/DataSharingService";
import {ProfileComponent} from "./Components/profile/profile.component";
// import {CompetitionCardComponent} from "./core/view/shared/widget/competition-card/competition-card.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, NavBarComponent, HttpClientModule, FormsModule , ProfileComponent ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  providers: [DataSharingService]
})

export class AppComponent {
  title = 'AFTAS-Front';
  isMemberDetailsRoute : boolean = false ;
  // Member: any;
}
