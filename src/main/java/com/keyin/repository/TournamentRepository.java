package com.keyin.repository;

import com.keyin.model.Tournament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long>, PagingAndSortingRepository<Tournament, Long> {
    Page<Tournament> findByLocation(String location, Pageable pageable);

    Page<Tournament> findByStartDate(LocalDate startDate, Pageable pageable);

    Page<Tournament> findByLocationAndStartDate(String location, LocalDate startDate, Pageable pageable);
}

