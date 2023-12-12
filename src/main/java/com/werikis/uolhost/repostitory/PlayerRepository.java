package com.werikis.uolhost.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.werikis.uolhost.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>{
    
}
