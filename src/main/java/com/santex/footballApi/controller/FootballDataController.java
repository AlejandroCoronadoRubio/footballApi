package com.santex.footballApi.controller;

import com.santex.footballApi.dto.CompetitionDTO;
import com.santex.footballApi.dto.PlayerDTO;
import com.santex.footballApi.dto.TeamDTO;
import com.santex.footballApi.service.CompetitionService;
import com.santex.footballApi.service.FootballDataService;
import com.santex.footballApi.service.PlayerService;
import com.santex.footballApi.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class FootballDataController {

    private final FootballDataService footballDataService;
    private final PlayerService playerService;
    private final TeamService teamService;
    private final CompetitionService competitionService;

    @PostMapping("/importLeague/{leagueCode}")
    public ResponseEntity<CompetitionDTO> importLeague(@PathVariable(name = "leagueCode") String leagueCode) {
        return ResponseEntity.ok(this.footballDataService.importCompetitionsAndTeamsByLeagueCode(leagueCode));
    }

    @PostMapping("/players/{leagueCode}")
    public ResponseEntity<Iterable<PlayerDTO>> players(@PathVariable(name = "leagueCode") String leagueCode) {
        return ResponseEntity.ok(this.playerService.getPlayersByLeagueCode(leagueCode));
    }

    @PostMapping("/team/{name}")
    public ResponseEntity<TeamDTO> team(@PathVariable(name = "name") String name) {
        return ResponseEntity.ok(this.teamService.getTeamByName(name));
    }

    @GetMapping("/competitions")
    public ResponseEntity<List<CompetitionDTO>> competitions() {
        return ResponseEntity.ok(this.competitionService.getCompetitions());
    }
}
