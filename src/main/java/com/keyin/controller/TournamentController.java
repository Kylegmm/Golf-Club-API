package com.keyin.controller;

import com.keyin.model.Tournament;
import com.keyin.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {
    @Autowired
    private TournamentService tournamentService;

    @PostMapping
    public ResponseEntity<Tournament> addTournament(@RequestBody Tournament tournament) {
        return ResponseEntity.ok(tournamentService.addTournament(tournament));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Tournament>> searchByLocation(@RequestParam String location) {
        return ResponseEntity.ok(tournamentService.searchByLocation(location));
    }
}
