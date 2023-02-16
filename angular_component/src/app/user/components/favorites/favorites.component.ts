import { Component, OnInit } from '@angular/core';
import { BookFullInformation } from 'src/app/core/DTOs/bookDto/book-full-information';
import { UserService } from 'src/app/core/services/userService/user-service';
import {Router} from '@angular/router'

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.sass'],

})
export class FavoritesComponent implements OnInit {

  public favoriteBooks: BookFullInformation[] | undefined;
  boll: Number = 1;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {

    this.userService.getFavoriteBooks().subscribe(books => { this.favoriteBooks = books });

  }

  removeFavoriteBook(bookFull: BookFullInformation){
    //Eliminar el renderizado del elemento
    this.userService.removeFavoriteBook(bookFull.book).subscribe((data) => {}); //put status message
  }

  goToShop(bookFull: BookFullInformation){
    this.router.navigateByUrl("/book/details/" + bookFull.book.id);
  }

}
