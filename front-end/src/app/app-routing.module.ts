/**
 * @author sowmya.lingaiah
 */
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WorkationComponent } from './components/workation/workation.component';

const routes: Routes = [
  { path: '', component: WorkationComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
