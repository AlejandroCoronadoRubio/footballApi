package com.santex.footballApi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santex.footballApi.entity.Competition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FootballDataService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String xAuthToken;
    private final String competitionsUrl;
    private final CompetitionService competitionService;
    private final PlayerService playerService;
    private final TeamService teamService;

    public FootballDataService(RestTemplate restTemplate, ObjectMapper objectMapper,
                               @Value("${footballApi.X-Auth-Token}") String xAuthToken,
                               @Value("${footballApi.competitionsUrl}") String competitionsUrl,
                               CompetitionService competitionService, PlayerService playerService, TeamService teamService) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.xAuthToken = xAuthToken;
        this.competitionsUrl = competitionsUrl;
        this.competitionService = competitionService;
        this.playerService = playerService;
        this.teamService = teamService;
    }

    public Competition importCompetitionsAndTeamsByLeagueCode(String leagueCode) {

        Competition competition = null;

        try {

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
            headers.set("X-Auth-Token", this.xAuthToken);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> competitionEntity = restTemplate.exchange(competitionsUrl + "/" + leagueCode + "/teams", HttpMethod.GET, entity, String.class);
            competition = objectMapper.readValue(competitionEntity.getBody(), Competition.class);

            this.competitionService.saveCompetition(competition);

        } catch(Exception e) {
            log.error("Error while fetching data from football api: " + e.getMessage());
            return competition;
        }

        return competition;
    }

}
