import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ImagepageComponent } from './imagepage/imagepage.component';


const routes: Routes = [
  {path: 'images', component: ImagepageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
