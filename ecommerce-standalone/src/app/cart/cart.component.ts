import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ICart, ICartItem, ICartTotals } from '../shared/model/cart';
import { Observable } from 'rxjs';
import { CartService } from './cart.service';
import {CartDetailsComponent} from "../core/cart-details/cart-details.component";


@Component({
    selector: 'app-cart',
    standalone: true,
    templateUrl: './cart.component.html',
    styleUrls: ['./cart.component.scss'],
    imports: [CommonModule, CartDetailsComponent]
})
export class CartComponent implements OnInit {
  cart$!: Observable<ICart | null>;
  cartTotals$!: Observable<ICartTotals | null>;

  constructor(public cartService: CartService) { }

  ngOnInit(): void {
    this.cart$ = this.cartService.cart$;
    this.cartTotals$ = this.cartService.cartTotals$;
  }

  incrementItemQuantity(item: ICartItem) {
    this.cartService.incrementItemQuantity(item);
  }

  decrementItemQuantity(item: ICartItem) {
    this.cartService.decrementItemQuantity(item);
  }

  removeCartItem(item: ICartItem) {
    this.cartService.removeItemFromCart(item);
  }
}
