import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreatePlayerComponent } from './create-player/create-player.component';
import { PlayerDetailsComponent } from './player-details/player-details.component';
import { PlayerListComponent } from './player-list/player-list.component';
import { HttpClientModule } from '@angular/common/http';
import { UpdatePlayerComponent } from './update-player/update-player.component';
@NgModule({
  declarations: [
    AppComponent,
    CreatePlayerComponent,
    PlayerDetailsComponent,
    PlayerListComponent,
    UpdatePlayerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
