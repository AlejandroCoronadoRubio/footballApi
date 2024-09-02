package com.santex.footballApi.service;

import com.santex.footballApi.entity.Team;
import com.santex.footballApi.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team findTeamByName(String name) {
        return this.teamRepository.findTeamByName(name);
    }
}
