import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookRoutingModule } from './book-routing.module';
import { ListComponent } from './components/list/list.component';
import { DetailsComponent } from './components/details/details.component';
import { CoreModule } from '../core/core.module';
import { CopyModule } from '../copy/copy.module';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [
    ListComponent,
    DetailsComponent,
  ],
  imports: [
    CommonModule,
    BookRoutingModule,
    CopyModule,
    SharedModule
  ]
})
export class BookModule { }
