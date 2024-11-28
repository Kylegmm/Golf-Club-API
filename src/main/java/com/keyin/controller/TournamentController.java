package com.keyin.controller;

import com.keyin.model.Member;
import com.keyin.model.Tournament;
import com.keyin.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {
    @Autowired
    private TournamentService tournamentService;

    @PostMapping("/{tournamentId}/addMember/{memberId}")
    public ResponseEntity<Tournament> addMemberToTournament(
            @PathVariable Long tournamentId,
            @PathVariable Long memberId) {
        Tournament updatedTournament = tournamentService.addMemberToTournament(tournamentId, memberId);
        return ResponseEntity.ok(updatedTournament);
    }
    @GetMapping("/{tournamentId}/members")
    public ResponseEntity<Set<Member>> getMembersInTournament(@PathVariable Long tournamentId) {
        Set<Member> members = tournamentService.getMembersInTournament(tournamentId);
        return ResponseEntity.ok(members);
    }
    @DeleteMapping("/{tournamentId}/removeMember/{memberId}")
    public ResponseEntity<Tournament> removeMemberFromTournament(
            @PathVariable Long tournamentId,
            @PathVariable Long memberId) {
        Tournament updatedTournament = tournamentService.removeMemberFromTournament(tournamentId, memberId);
        return ResponseEntity.ok(updatedTournament);
    }
    @GetMapping("/search")
    public ResponseEntity<Page<Tournament>> searchTournaments(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "startDate,asc") String sort) {

        // Split sort parameter into field and direction
        String[] sortParams = sort.split(",");
        String sortField = sortParams[0];
        Sort.Direction sortDirection = sortParams.length > 1 && sortParams[1].equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortField));

        if (location != null && startDate != null) {
            return ResponseEntity.ok(tournamentService.searchByLocationAndStartDate(location, startDate, pageable));
        } else if (location != null) {
            return ResponseEntity.ok(tournamentService.searchByLocation(location, pageable));
        } else if (startDate != null) {
            return ResponseEntity.ok(tournamentService.searchByStartDate(startDate, pageable));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
