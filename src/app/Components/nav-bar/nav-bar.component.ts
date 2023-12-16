import { Component } from '@angular/core';
import {SearchBarComponent} from "../search-bar/search-bar.component";

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [
    SearchBarComponent
  ],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent {

}
