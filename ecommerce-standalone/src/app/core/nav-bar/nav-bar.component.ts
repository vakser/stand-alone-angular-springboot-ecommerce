import { Component } from '@angular/core';
import {CommonModule} from "@angular/common";
import {Observable} from "rxjs";
import {ICart} from "../../shared/model/cart";
import {CartService} from "../../cart/cart.service";
import {RouterModule} from "@angular/router";

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule
  ],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent {
  cart$: Observable<ICart | null>;

  constructor(public cartService: CartService) {
    this.cart$ = this.cartService.cart$;
  }
}
