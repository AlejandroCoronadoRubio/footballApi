package com.santex.footballApi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santex.footballApi.dto.CompetitionDTO;
import com.santex.footballApi.entity.Competition;
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
    private final CompetitionService competitionService;
    private final String xAuthToken;
    private final String competitionsUrl;

    public FootballDataService(RestTemplate restTemplate, ObjectMapper objectMapper, ModelMapper modelMapper, CompetitionService competitionService,
                               @Value("${footballApi.X-Auth-Token}") String xAuthToken,
                               @Value("${footballApi.competitionsUrl}") String competitionsUrl) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.modelMapper = modelMapper;
        this.competitionService = competitionService;
        this.xAuthToken = xAuthToken;
        this.competitionsUrl = competitionsUrl;
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

        Competition competition = this.objectMapper.readValue(competitionEntity.getBody(), Competition.class);
        JsonNode jsonNode = this.objectMapper.readTree(competitionEntity.getBody());
        competition.setId(jsonNode.get("competition").get("id").asLong());
        competition.setName(jsonNode.get("competition").get("name").asText());
        competition.setCode(jsonNode.get("competition").get("code").asText());
        competition.setAreaName(jsonNode.get("teams").get(0).get("area").get("name").asText());

        competition.getTeams().forEach(team -> {
            team.setCompetition(competition);
            team.getCoach().setTeam(team);
            team.getPlayers().forEach(player -> player.setTeam(team));
        });

        return competition;
    }

}
