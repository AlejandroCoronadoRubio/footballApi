package com.santex.footballApi.service;

import com.santex.footballApi.dto.CompetitionDTO;
import com.santex.footballApi.entity.Competition;
import com.santex.footballApi.repository.CompetitionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final ModelMapper modelMapper;

    public CompetitionService(CompetitionRepository competitionRepository, ModelMapper modelMapper) {
        this.competitionRepository = competitionRepository;
        this.modelMapper = modelMapper;
    }

    public void saveCompetition(Competition competition) {
        this.competitionRepository.save(competition);
    }

    public List<CompetitionDTO> getCompetitions() {
        List<Competition> competitions = this.competitionRepository.findAll();
        Type type = new TypeToken<List<CompetitionDTO>>(){}.getType();
        return this.modelMapper.map(competitions, type);
    }
}
