import {Component, OnInit} from '@angular/core';
import {AsyncPipe, CommonModule, NgIf, NgOptimizedImage} from "@angular/common";
import {Observable} from "rxjs";
import {ICart} from "../../shared/model/cart";
import {CartService} from "../../cart/cart.service";
import {RouterLink, RouterLinkActive, RouterModule} from "@angular/router";
import {KeycloakProfile} from "keycloak-js";
import {KeycloakService} from "keycloak-angular";

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    NgOptimizedImage,
    RouterLink,
    RouterLinkActive,
    NgIf,
    AsyncPipe
  ],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent implements OnInit {
  cart$: Observable<ICart | null>;
  public loggedIn = false;
  public userProfile : KeycloakProfile | null = null;

  constructor(public cartService: CartService, private keycloak: KeycloakService) {
    this.cart$ = this.cartService.cart$;
  }

  ngOnInit(): void {
    this.isLogged();
  }

  async isLogged() {
    this.loggedIn = this.keycloak.isLoggedIn();
    console.log("LoggedIn?");
    console.log(this.loggedIn);
    if (this.loggedIn) {
      this.userProfile = await this.keycloak.loadUserProfile();
      console.log(this.userProfile);
    }
  }

  public login() {
    this.keycloak.login();
  }

  public logout() {
    this.keycloak.logout();
  }

  get username() {
    return this.userProfile?.username;
  }

  // get email() {
  //   return this.userProfile?.email;
  // }
}
