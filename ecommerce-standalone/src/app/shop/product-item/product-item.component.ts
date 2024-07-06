import {Component, Input} from '@angular/core';
import {CurrencyPipe, NgIf} from "@angular/common";
import {IProduct} from "../../shared/model/product";

@Component({
  selector: 'app-product-item',
  standalone: true,
  imports: [
    NgIf,
    CurrencyPipe
  ],
  templateUrl: './product-item.component.html',
  styleUrl: './product-item.component.css'
})
export class ProductItemComponent {
  @Input() product?: IProduct;
}
