import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/core/services/userService/user-service';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.sass']
})
export class ShoppingCartComponent implements OnInit {

  constructor(public userService: UserService) { }

  ngOnInit(): void {
    this.userService.getShoppingCart().subscribe(data => {
      console.log(data);
    })
  }

}
