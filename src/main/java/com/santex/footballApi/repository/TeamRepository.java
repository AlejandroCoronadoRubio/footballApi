package com.santex.footballApi.repository;

import com.santex.footballApi.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findTeamByName(String name);
}
