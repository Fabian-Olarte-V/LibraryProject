import { Component, OnInit } from '@angular/core';
import { BookFullInformation } from 'src/app/core/DTOs/bookDto/book-full-information';
import { UserService } from 'src/app/core/services/userService/user-service';

@Component({
  selector: 'app-recommended-books',
  templateUrl: './recommended-books.component.html',
  styleUrls: ['./recommended-books.component.sass']
})
export class RecommendedBooksComponent implements OnInit {

  books: BookFullInformation[] | undefined;

  constructor(public userService: UserService) { }

  ngOnInit(): void {
    this.userService.getRecommendedBooks().subscribe(books => {
      this.books = books;

      this.userService.getFavoriteBooks().subscribe(fBooks => {
        books.forEach(element => {
          element.minCopyPrice = element.copies.reduce(function(prev, curr) { return prev.price < curr.price ? prev: curr });
          element.maxCopyPrice = element.copies.reduce(function(prev, curr){ return prev.price > curr.price ? prev: curr });
          fBooks.forEach(fB => { if(fB.book.id === element.book.id){ element.isFavorite = true }});
        });
      });
    });
  }

  addFavoriteBook(bookCopiesAuthorsDTO: BookFullInformation): void{
    bookCopiesAuthorsDTO.isFavorite = true;
    this.userService.addFavoriteBook(bookCopiesAuthorsDTO.book).subscribe((data) => {});
  }

  removeFavoriteBook(bookCopiesAuthorsDTO: BookFullInformation): void{
    bookCopiesAuthorsDTO.isFavorite = false;
    this.userService.removeFavoriteBook(bookCopiesAuthorsDTO.book).subscribe((data) => {}); //put status message
  }

}
