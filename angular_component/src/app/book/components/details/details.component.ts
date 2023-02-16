import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BookFullInformation } from 'src/app/core/DTOs/bookDto/book-full-information';
import { Author } from 'src/app/core/model/author/author';
import { BookService } from 'src/app/core/services/bookService/book-service';
import { UserService } from 'src/app/core/services/userService/user-service';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.sass']
})

export class DetailsComponent implements OnInit {

  bookInfo: BookFullInformation | undefined;
  authors: Author[] | undefined;
  id: Number | undefined;

  constructor(private bookService: BookService, private userService: UserService, private router: ActivatedRoute) { }

  ngOnInit(): void {

    this.bookService.getBookById(this.router.snapshot.params['id']).subscribe(book => { 
      this.bookInfo = book;

      this.userService.getFavoriteBooks().subscribe(fBooks => {
        fBooks.forEach(fB => { if(fB.book.id === this.bookInfo?.book.id) { this.bookInfo.isFavorite = true }});
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
