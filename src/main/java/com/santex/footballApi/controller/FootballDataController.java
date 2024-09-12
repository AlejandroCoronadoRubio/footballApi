package com.santex.footballApi.controller;

import com.santex.footballApi.dto.PlayerDTO;
import com.santex.footballApi.dto.TeamDTO;
import com.santex.footballApi.entity.Competition;
import com.santex.footballApi.service.FootballDataService;
import com.santex.footballApi.service.PlayerService;
import com.santex.footballApi.service.TeamService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class FootballDataController {

    private final FootballDataService footballDataService;
    private final PlayerService playerService;
    private final TeamService teamService;


    public FootballDataController(FootballDataService footballDataService, PlayerService playerService, TeamService teamService) {
        this.footballDataService = footballDataService;
        this.playerService = playerService;
        this.teamService = teamService;
    }

    @MutationMapping
    public Competition importLeague(@Argument(name = "leagueCode") String leagueCode) {
        return this.footballDataService.importCompetitionsAndTeamsByLeagueCode(leagueCode);
    }

    @QueryMapping
    public Iterable<PlayerDTO> players(@Argument(name = "leagueCode") String leagueCode) {
        return this.playerService.getPlayersByLeagueCode(leagueCode);
    }

    @QueryMapping
    public TeamDTO team(@Argument(name = "name") String name) {
        return this.teamService.getTeamByName(name);
    }

}
