package com.werikis.uolhost.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.werikis.uolhost.infra.CodinameHandler;
import com.werikis.uolhost.model.GroupType;
import com.werikis.uolhost.model.Player;
import com.werikis.uolhost.model.dtos.PlayerDto;
import com.werikis.uolhost.repostitory.PlayerRepository;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private CodinameHandler codinameHandler;

    public Player createPlayer(PlayerDto dto) {
        Player newPlayer = new Player(dto);
        String codiname = getCodiname(dto.groupType());
        newPlayer.setCodiname(codiname);
        return playerRepository.save(newPlayer);
    }
    
    private String getCodiname(GroupType groupType){
        return codinameHandler.findCodiname(groupType);
    }

    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }


}
