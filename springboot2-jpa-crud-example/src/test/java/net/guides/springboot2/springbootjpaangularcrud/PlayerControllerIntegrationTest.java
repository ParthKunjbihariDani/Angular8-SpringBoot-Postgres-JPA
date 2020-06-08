package net.guides.springboot2.springbootjpaangularcrud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import net.guides.springboot2.springbootjpaangularcrud.Application;
import net.guides.springboot2.springbootjpaangularcrud.model.Player;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlayerControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllPlayers() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/players",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetPlayerById() {
		Player player = restTemplate.getForObject(getRootUrl() + "/players/1", Player.class);
		System.out.println(player.getFirstName());
		assertNotNull(player);
	}

	@Test
	public void testCreatePlayer() {
		Player player = new Player();
		player.setEmailId("admin@gmail.com");
		player.setFirstName("admin");
		player.setLastName("admin");

		ResponseEntity<Player> postResponse = restTemplate.postForEntity(getRootUrl() + "/players", player, Player.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdatePlayer() {
		int id = 1;
		Player player = restTemplate.getForObject(getRootUrl() + "/players/" + id, Player.class);
		player.setFirstName("admin1");
		player.setLastName("admin2");

		restTemplate.put(getRootUrl() + "/players/" + id, player);

		Player updatedPlayer = restTemplate.getForObject(getRootUrl() + "/players/" + id, Player.class);
		assertNotNull(updatedPlayer);
	}

	@Test
	public void testDeletePlayer() {
		int id = 2;
		Player player = restTemplate.getForObject(getRootUrl() + "/players/" + id, Player.class);
		assertNotNull(player);

		restTemplate.delete(getRootUrl() + "/players/" + id);

		try {
			player = restTemplate.getForObject(getRootUrl() + "/players/" + id, Player.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
