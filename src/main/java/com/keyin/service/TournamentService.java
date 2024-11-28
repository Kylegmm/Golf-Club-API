package com.keyin.service;

import com.keyin.model.Member;
import com.keyin.model.Tournament;
import com.keyin.repository.MemberRepository;
import com.keyin.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {
    @Autowired
    private TournamentRepository tournamentRepository;

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
}

