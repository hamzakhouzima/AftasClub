// import {Component, Input, OnDestroy, OnInit} from '@angular/core';
// import {Member} from "../../Model/Member";
// import {CommonModule, NgForOf} from "@angular/common";
// import {Subscription} from "rxjs";
// import {MemberService} from "../../Service/MemberService";
// import {DataSharingService} from "../../Service/DataSharingService";
// import {FormsModule} from "@angular/forms";
// import {HttpClientModule} from "@angular/common/http";
//
// @Component({
//   selector: 'app-cards',
//   standalone: true,
//   imports: [
//     NgForOf, CommonModule,
//     HttpClientModule,
//     FormsModule
//   ],
//   templateUrl: './cards.component.html',
//   styleUrl: './cards.component.css'
// })
// // export class CardsComponent implements OnInit, OnDestroy {
// export class CardsComponent implements OnInit, OnDestroy {
//   foundMembers: Member = {} as Member;
//   private subscription: Subscription = new Subscription();
//
//   constructor(private dataSharingService: DataSharingService) {}
//
//   ngOnInit(): void {
//     this.subscription = this.dataSharingService.foundMembers$.subscribe(
//       (members: Member) => {
//         this.foundMembers = members;
//       }
//     );
//   }
//
//   ngOnDestroy(): void {
//     this.subscription.unsubscribe();
//   }
// }
