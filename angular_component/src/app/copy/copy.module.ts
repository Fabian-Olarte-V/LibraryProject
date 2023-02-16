import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CopyRoutingModule } from './copy-routing.module';
import { OptionsComponent } from './components/options/options.component';


@NgModule({
  declarations: [
    OptionsComponent
  ],
  imports: [
    CommonModule,
    CopyRoutingModule
  ],
  exports:[
    OptionsComponent
  ]
})
export class CopyModule { }
