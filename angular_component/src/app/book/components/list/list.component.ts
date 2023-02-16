import { Component, OnInit } from '@angular/core';
import { BookFullInformation } from 'src/app/core/DTOs/bookDto/book-full-information';
import { Book } from 'src/app/core/model/book/book';
import { User } from 'src/app/core/model/user/user';
import { BookService } from 'src/app/core/services/bookService/book-service';
import { UserService } from 'src/app/core/services/userService/user-service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.sass']
})
export class ListComponent implements OnInit {

  books: BookFullInformation[] | undefined;
  favoriteBooks: Book[] | undefined;
  account: User | undefined;

  constructor(private bookService: BookService, private userService: UserService) { }

  ngOnInit(): void {

    this.bookService.getBookList().subscribe(books => {    
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
