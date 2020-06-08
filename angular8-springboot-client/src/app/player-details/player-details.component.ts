import { Player } from '../player';
import { Component, OnInit, Input } from '@angular/core';
import { PlayerService } from '../player.service';
import { PlayerListComponent } from '../player-list/player-list.component';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-player-details',
  templateUrl: './player-details.component.html',
  styleUrls: ['./player-details.component.css']
})
export class PlayerDetailsComponent implements OnInit {

  id: number;
  player: Player;

  constructor(private route: ActivatedRoute,private router: Router,
    private playerService: PlayerService) { }

  ngOnInit() {
    this.player = new Player();

    this.id = this.route.snapshot.params['id'];
    
    this.playerService.getPlayer(this.id)
      .subscribe(data => {
        console.log(data)
        this.player = data;
      }, error => console.log(error));
  }

  list(){
    this.router.navigate(['players']);
  }
}
