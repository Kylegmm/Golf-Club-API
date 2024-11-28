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

    // Existing methods...

    @PostMapping("/{tournamentId}/addMember/{memberId}")
    public ResponseEntity<Tournament> addMemberToTournament(
            @PathVariable Long tournamentId,
            @PathVariable Long memberId) {
        Tournament updatedTournament = tournamentService.addMemberToTournament(tournamentId, memberId);
        return ResponseEntity.ok(updatedTournament);
    }
}
