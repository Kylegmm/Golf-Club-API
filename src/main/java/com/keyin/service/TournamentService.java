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
import java.util.List;
import java.util.Set;

@Service
public class TournamentService {
    @Autowired
    private TournamentRepository tournamentRepository;

    public Page<Tournament> searchByLocation(String location, Pageable pageable) {
        return tournamentRepository.findByLocation(location, pageable);
    }

    public Page<Tournament> searchByStartDate(LocalDate startDate, Pageable pageable) {
        return tournamentRepository.findByStartDate(startDate, pageable);
    }

    public Page<Tournament> searchByLocationAndStartDate(String location, LocalDate startDate, Pageable pageable) {
        return tournamentRepository.findByLocationAndStartDate(location, startDate, pageable);
    }

    @Autowired
    private MemberRepository memberRepository;

    public Tournament addMemberToTournament(Long tournamentId, Long memberId) {
        // Fetch the tournament
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new IllegalArgumentException("Tournament not found with ID: " + tournamentId));

        // Fetch the member
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with ID: " + memberId));

        // Add the member to the tournament
        tournament.getMembers().add(member);

        // Save and return the updated tournament
        return tournamentRepository.save(tournament);
    }
    public Set<Member> getMembersInTournament(Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new IllegalArgumentException("Tournament not found with ID: " + tournamentId));
        return tournament.getMembers();
    }
    public Tournament removeMemberFromTournament(Long tournamentId, Long memberId) {
        // Fetch the tournament
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new IllegalArgumentException("Tournament not found with ID: " + tournamentId));

        // Fetch the member
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with ID: " + memberId));

        // Remove the member
        tournament.getMembers().remove(member);

        // Save the updated tournament
        return tournamentRepository.save(tournament);
    }
}

