import { Component, OnInit } from '@angular/core';
import { Player } from '../player';
import { ActivatedRoute, Router } from '@angular/router';
import { PlayerService } from '../player.service';

@Component({
  selector: 'app-update-player',
  templateUrl: './update-player.component.html',
  styleUrls: ['./update-player.component.css']
})
export class UpdatePlayerComponent implements OnInit {

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

  updatePlayer() {
    this.playerService.updatePlayer(this.id, this.player)
      .subscribe(data => console.log(data), error => console.log(error));
    this.player = new Player();
    this.gotoList();
  }

  onSubmit() {
    this.updatePlayer();    
  }

  gotoList() {
    this.router.navigate(['/players']);
  }
}
