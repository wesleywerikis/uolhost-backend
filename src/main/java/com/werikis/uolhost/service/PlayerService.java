package com.werikis.uolhost.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.werikis.uolhost.model.Player;
import com.werikis.uolhost.model.dtos.PlayerDto;
import com.werikis.uolhost.repostitory.PlayerRepository;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Player createPlayer(PlayerDto dto) {
        Player newPlayer = new Player(dto);
        return playerRepository.save(newPlayer);
    }
    
}
