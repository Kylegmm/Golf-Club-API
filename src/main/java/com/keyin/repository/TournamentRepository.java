package com.keyin.repository;

import com.keyin.model.Tournament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    // Find tournaments by location with pagination
    Page<Tournament> findByLocation(String location, Pageable pageable);

    // Find tournaments by start date with pagination
    Page<Tournament> findByStartDate(LocalDate startDate, Pageable pageable);

    // Find tournaments by location and start date with pagination
    Page<Tournament> findByLocationAndStartDate(String location, LocalDate startDate, Pageable pageable);
}
