package com.santex.footballApi.repository;

import com.santex.footballApi.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}
