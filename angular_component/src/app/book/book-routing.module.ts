import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DetailsComponent } from './components/details/details.component';
import { ListComponent } from './components/list/list.component';


const routes: Routes = [
  {path: '', children: [
    {path: '', redirectTo: '/list', pathMatch: 'full'},
    {path: 'list', component: ListComponent},
    {path: 'details/:id', component: DetailsComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class BookRoutingModule { }
