package com.santex.footballApi.service;

import com.santex.footballApi.dto.PlayerDTO;
import com.santex.footballApi.entity.Player;
import com.santex.footballApi.repository.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;

    public PlayerService(PlayerRepository playerRepository, ModelMapper modelMapper) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
    }

    public List<PlayerDTO> getPlayersByLeagueCode(String leagueCode) {
        List<Player> players = this.playerRepository.findByTeamCompetitionCode(leagueCode);
        Type listType = new TypeToken<List<PlayerDTO>>(){}.getType();
        return modelMapper.map(players, listType);
    }

}
