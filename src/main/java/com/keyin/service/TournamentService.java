package com.keyin.service;

import com.keyin.model.Tournament;
import com.keyin.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {
    @Autowired
    private TournamentRepository tournamentRepository;

    public Tournament addTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public List<Tournament> searchByLocation(String location) {
        return tournamentRepository.findByLocation(location);
    }
}
