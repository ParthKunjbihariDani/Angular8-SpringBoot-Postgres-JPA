package net.guides.springboot2.springbootjpaangularcrud.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.guides.springboot2.springbootjpaangularcrud.exception.ResourceNotFoundException;
import net.guides.springboot2.springbootjpaangularcrud.model.Player;
import net.guides.springboot2.springbootjpaangularcrud.repository.PlayerRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class PlayersController { 
	@Autowired
	private PlayerRepository playerRepository;

	@GetMapping("/players")
	public List<Player> getAllPlayers() {
		return playerRepository.findAll();
	}

	@GetMapping("/players/{id}")
	public ResponseEntity<Player> getPlayerById(@PathVariable(value = "id") Long playerId)
			throws ResourceNotFoundException {
		Player player = playerRepository.findById(playerId)
				.orElseThrow(() -> new ResourceNotFoundException("Player not found for this id :: " + playerId));
		return ResponseEntity.ok().body(player);
	}

	@PostMapping("/players")
	public Player createPlayer(@Valid @RequestBody Player player) {
		return playerRepository.save(player);
	}

	@PutMapping("/players/{id}")
	public ResponseEntity<Player> updatePlayer(@PathVariable(value = "id") Long playerId,
			@Valid @RequestBody Player playerDetails) throws ResourceNotFoundException {
		Player player = playerRepository.findById(playerId)
				.orElseThrow(() -> new ResourceNotFoundException("Player not found for this id :: " + playerId));

		player.setEmailId(playerDetails.getEmailId());
		player.setLastName(playerDetails.getLastName());
		player.setFirstName(playerDetails.getFirstName());
		final Player updatedPlayer = playerRepository.save(player);
		return ResponseEntity.ok(updatedPlayer);
	}

	@DeleteMapping("/players/{id}")
	public Map<String, Boolean> deletePlayer(@PathVariable(value = "id") Long playerId)
			throws ResourceNotFoundException {
		Player player = playerRepository.findById(playerId)
				.orElseThrow(() -> new ResourceNotFoundException("Player not found for this id :: " + playerId));

		playerRepository.delete(player);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
