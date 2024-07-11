import { Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";

export const routes: Routes = [
  {path: '', redirectTo: 'welcome', pathMatch: 'full'},
  {path: 'welcome', component:HomeComponent},
  {path: 'shop', loadChildren: () => import('./shop/shop.routes').then(r => r.SHOP_ROUTES)},
  /*  {
    path: 'cart',
    loadComponent: () => import('./cart/cart.component').then(c => c.CartComponent)
  },   */
  {path: 'cart', loadChildren: () => import('./cart/cart.routes').then(r => r.CART_ROUTES)}
  // { path: '**', redirectTo: 'welcome', pathMatch: 'full' }
];
