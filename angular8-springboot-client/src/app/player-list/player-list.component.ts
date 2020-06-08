import { PlayerDetailsComponent } from '../player-details/player-details.component';
import { Observable } from "rxjs";
import { PlayerService } from "../player.service";
import { Player } from "../player";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

@Component({
  selector: "app-player-list",
  templateUrl: "./player-list.component.html",
  styleUrls: ["./player-list.component.css"]
})
export class PlayerListComponent implements OnInit {
  players: Observable<Player[]>;

  constructor(private playerService: PlayerService,
    private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.players = this.playerService.getPlayersList();
  }

  /*createPlayer(player: Object){
    this.router.navigate(['create', player]);
  }*/

  deletePlayer(id: number) {
    this.playerService.deletePlayer(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  playerDetails(id: number){
    this.router.navigate(['details', id]);
  }

  updatePlayer(id: number){
    this.router.navigate(['update', id]);
  }
}
