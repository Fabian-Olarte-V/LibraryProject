import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FavoritesComponent } from './components/favorites/favorites.component';
import { RecommendedBooksComponent } from './components/recommended-books/recommended-books.component';
import { ShoppingCartComponent } from './components/shopping-cart/shopping-cart.component';

const routes: Routes = [
  {path: '', children: [
    {path: 'favorites', component: FavoritesComponent},
    {path: 'shoppingCart', component: ShoppingCartComponent},
    {path: 'recommended-books', component: RecommendedBooksComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class UserRoutingModule { }
