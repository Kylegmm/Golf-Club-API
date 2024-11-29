package com.keyin.service;

import com.keyin.model.Member;
import com.keyin.model.Tournament;
import com.keyin.repository.MemberRepository;
import com.keyin.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private MemberRepository memberRepository;

    // CRUD Operations for Tournaments

    // Create a new tournament
    public Tournament createTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    // Get a tournament by ID
    public Tournament getTournamentById(Long id) {
        return tournamentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tournament not found with ID: " + id));
    }

    // Update an existing tournament
    public Tournament updateTournament(Long id, Tournament updatedTournament) {
        Tournament existingTournament = tournamentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tournament not found with ID: " + id));

        existingTournament.setStartDate(updatedTournament.getStartDate());
        existingTournament.setEndDate(updatedTournament.getEndDate());
        existingTournament.setLocation(updatedTournament.getLocation());
        existingTournament.setEntryFee(updatedTournament.getEntryFee());
        existingTournament.setCashPrize(updatedTournament.getCashPrize());

        return tournamentRepository.save(existingTournament);
    }

    // Delete a tournament
    public boolean deleteTournament(Long id) {
        if (tournamentRepository.existsById(id)) {
            tournamentRepository.deleteById(id);
            return true;
        } else {
            throw new IllegalArgumentException("Tournament not found with ID: " + id);
        }
    }

    // Search Methods

    public Page<Tournament> searchByLocation(String location, Pageable pageable) {
        return tournamentRepository.findByLocation(location, pageable);
    }

    public Page<Tournament> searchByStartDate(LocalDate startDate, Pageable pageable) {
        return tournamentRepository.findByStartDate(startDate, pageable);
    }

    public Page<Tournament> searchByLocationAndStartDate(String location, LocalDate startDate, Pageable pageable) {
        return tournamentRepository.findByLocationAndStartDate(location, startDate, pageable);
    }

    // Member Management in Tournaments

    public Tournament addMemberToTournament(Long tournamentId, Long memberId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new IllegalArgumentException("Tournament not found with ID: " + tournamentId));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with ID: " + memberId));

        tournament.getMembers().add(member);

        return tournamentRepository.save(tournament);
    }

    public Set<Member> getMembersInTournament(Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new IllegalArgumentException("Tournament not found with ID: " + tournamentId));

        return tournament.getMembers();
    }

    public Tournament removeMemberFromTournament(Long tournamentId, Long memberId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new IllegalArgumentException("Tournament not found with ID: " + tournamentId));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with ID: " + memberId));

        tournament.getMembers().remove(member);

        return tournamentRepository.save(tournament);
    }
}
