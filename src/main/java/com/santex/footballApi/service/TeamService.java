package com.santex.footballApi.service;

import com.santex.footballApi.dto.TeamDTO;
import com.santex.footballApi.entity.Team;
import com.santex.footballApi.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;

    public TeamService(TeamRepository teamRepository, ModelMapper modelMapper) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
    }

    public TeamDTO getTeamByName(String name) {
        Team team = this.teamRepository.findTeamByName(name);
        return modelMapper.map(team, TeamDTO.class);
    }
}
