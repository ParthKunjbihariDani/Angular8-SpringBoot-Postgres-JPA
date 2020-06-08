import { PlayerService } from '../player.service';
import { Player } from '../player';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-player',
  templateUrl: './create-player.component.html',
  styleUrls: ['./create-player.component.css']
})
export class CreatePlayerComponent implements OnInit {

  player: Player = new Player();
  submitted = false;

  constructor(private playerService: PlayerService,
    private router: Router) { }

  ngOnInit() {
  }

  newEmployee(): void {
    this.submitted = false;
    this.player = new Player();
  }

  save() {
    this.playerService.createPlayer(this.player)
      .subscribe(data => console.log(data), error => console.log(error));
    this.player = new Player();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList() {
    this.router.navigate(['/players']);
  }
}
