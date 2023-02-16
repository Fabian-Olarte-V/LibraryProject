import { Component, Input, OnInit } from '@angular/core';
import { BookFullInformation } from 'src/app/core/DTOs/bookDto/book-full-information';
import { BookService } from 'src/app/core/services/bookService/book-service';
import { UserService } from 'src/app/core/services/userService/user-service';

@Component({
  selector: 'app-book-cover',
  templateUrl: './book-cover.component.html',
  styleUrls: ['./book-cover.component.sass']
})
export class BookCoverComponent implements OnInit {

  @Input()
  bookFullInfo: BookFullInformation | undefined;

  constructor(private bookService: BookService, private userService: UserService) { }

  ngOnInit(): void {

  }

}
