import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserRoutingModule } from './user-routing.module';
import { CoreModule } from '../core/core.module';
import { FavoritesComponent } from './components/favorites/favorites.component';
import { ShoppingCartComponent } from './components/shopping-cart/shopping-cart.component';
import { RecommendedBooksComponent } from './components/recommended-books/recommended-books.component';
import { SharedModule } from '../shared/shared.module';

@NgModule({
  declarations: [
    FavoritesComponent,
    ShoppingCartComponent,
    RecommendedBooksComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    SharedModule
  ]
})

export class UserModule { }
