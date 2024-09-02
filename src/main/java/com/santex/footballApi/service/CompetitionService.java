package com.santex.footballApi.service;

import com.santex.footballApi.entity.Competition;
import com.santex.footballApi.repository.CompetitionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionService {

    private final CompetitionRepository competitionRepository;

    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public void saveCompetition(Competition competition) {
        this.competitionRepository.save(competition);
    }
}
