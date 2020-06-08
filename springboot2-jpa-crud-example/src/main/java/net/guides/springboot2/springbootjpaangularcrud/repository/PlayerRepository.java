package net.guides.springboot2.springbootjpaangularcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.guides.springboot2.springbootjpaangularcrud.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{

}
