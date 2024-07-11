import {Component, OnInit} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {NavBarComponent} from "./core/nav-bar/nav-bar.component";
import {ShopComponent} from "./shop/shop.component";
import {CommonModule} from "@angular/common";
import {CartService} from "./cart/cart.service";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, NavBarComponent, ShopComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  pageTitle = 'Welcome to Stylish Online Shop';

  constructor(private cartService: CartService) { }

  ngOnInit(): void {
    this.loadBasket();
  }

  loadBasket() {
    const cartId = localStorage.getItem('angular_cart_id');
    if (cartId) {
      this.cartService.getCart(cartId).subscribe({
        next: response => {
          console.log("Cart Initialized");
          console.log(response);
        },
        error: error=> console.log(error)
      });
    }
  }

}
