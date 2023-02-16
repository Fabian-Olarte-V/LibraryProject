import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookCoverComponent } from './components/book-cover/book-cover.component';
import { BookCardComponent } from './components/book-card/book-card.component';

@NgModule({
  declarations: [
    BookCoverComponent,
    BookCardComponent
  ],
  imports: [
    CommonModule,
    
  ],
  exports: [
    BookCoverComponent,
    BookCardComponent
  ]
})

export class SharedModule { }
