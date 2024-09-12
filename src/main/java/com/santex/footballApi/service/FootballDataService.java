package com.santex.footballApi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santex.footballApi.dto.CompetitionDTO;
import com.santex.footballApi.entity.Competition;
import com.santex.footballApi.entity.Player;
import com.santex.footballApi.entity.Team;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FootballDataService {

    private static final Logger logger = LoggerFactory.getLogger(FootballDataService.class);
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;
    private final String xAuthToken;
    private final String competitionsUrl;
    private final CompetitionService competitionService;

    public FootballDataService(RestTemplate restTemplate, ObjectMapper objectMapper, ModelMapper modelMapper,
                               @Value("${footballApi.X-Auth-Token}") String xAuthToken,
                               @Value("${footballApi.competitionsUrl}") String competitionsUrl,
                               CompetitionService competitionService) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.modelMapper = modelMapper;
        this.xAuthToken = xAuthToken;
        this.competitionsUrl = competitionsUrl;
        this.competitionService = competitionService;
    }

    public CompetitionDTO importCompetitionsAndTeamsByLeagueCode(String leagueCode) {

        Competition competition = null;

        try {
            logger.info("Starting fetching data from football api on FootballDataService::importCompetitionsAndTeamsByLeagueCode");

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
            headers.set("X-Auth-Token", this.xAuthToken);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> competitionEntity = restTemplate.exchange(competitionsUrl.replaceFirst("leagueCode", leagueCode), HttpMethod.GET, entity, String.class);
            competition = this.createCompetitionObject(competitionEntity);

            this.competitionService.saveCompetition(competition);

        } catch(JsonProcessingException | RestClientException e) {
            logger.error("Error while fetching data from football api on FootballDataService::importCompetitionsAndTeamsByLeagueCode: {}", e.getMessage());
        }

        return modelMapper.map(competition, CompetitionDTO.class);
    }

    private Competition createCompetitionObject(ResponseEntity<String> competitionEntity) throws JsonProcessingException {

        Competition competition = objectMapper.readValue(competitionEntity.getBody(), Competition.class);
        JsonNode jsonNode = objectMapper.readTree(competitionEntity.getBody());
        competition.setId(jsonNode.get("competition").get("id").asLong());
        competition.setName(jsonNode.get("competition").get("name").asText());
        competition.setCode(jsonNode.get("competition").get("code").asText());
        competition.setAreaName(jsonNode.get("teams").get(0).get("area").get("name").asText());

        for(Team team : competition.getTeams()) {
            team.setCompetition(competition);
            team.getCoach().setTeam(team);
            for(Player player: team.getPlayers()) {
                player.setTeam(team);
            }
        }

        return competition;
    }

}
