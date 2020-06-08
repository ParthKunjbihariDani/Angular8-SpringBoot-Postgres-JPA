import { PlayerDetailsComponent } from './player-details/player-details.component';
import { CreatePlayerComponent } from './create-player/create-player.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PlayerListComponent } from './player-list/player-list.component';
import { UpdatePlayerComponent } from './update-player/update-player.component';

const routes: Routes = [
  { path: '', redirectTo: 'player', pathMatch: 'full' },
  { path: 'players', component: PlayerListComponent },
  { path: 'add', component: CreatePlayerComponent },
  { path: 'update/:id', component: UpdatePlayerComponent },
  { path: 'details/:id', component: PlayerDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
