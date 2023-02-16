import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Copy } from 'src/app/core/model/copy/copy';
import { BookService } from 'src/app/core/services/bookService/book-service';
import { UserService } from 'src/app/core/services/userService/user-service';

@Component({
  selector: 'app-options',
  templateUrl: './options.component.html',
  styleUrls: ['./options.component.sass']
})

export class OptionsComponent implements OnInit {

  copies: Copy[] | undefined;
  copiesAddedCart: Copy[] | undefined;

  constructor(private bookService: BookService, private userService: UserService, private router: ActivatedRoute) { }

  ngOnInit(): void {

    this.bookService.getCopiesByBookId(this.router.snapshot.params['id']).subscribe(copies => {
      this.copies = copies;

      this.userService.getShoppingCart().subscribe(copiesCart => { this.copiesAddedCart = copiesCart;
      console.log(copiesCart)});
      
    });

  }

  addToShoppingCart(copy: Copy){

    console.log("in")

    if(!this.copiesAddedCart?.includes(copy)){
      this.userService.addCopyToShoppingCart(copy).subscribe({});
      console.log("added")
    }
    else{
      //Alert copy already added
    }

  }

  removeFromShoppingCart(copy: Copy){

    if(this.copiesAddedCart?.includes(copy)){
      this.userService.removeCopyFromShoppingCart(copy).subscribe({});
      console.log("removed")
    }
    else{
      //Alert copy already added
    }

  }



}
