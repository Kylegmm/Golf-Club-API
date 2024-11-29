package com.keyin.service;

import com.keyin.model.Member;
import com.keyin.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // Add a new member
    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    // Get all members
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // Search members by name (case-insensitive)
    public List<Member> searchByName(String name) {
        return memberRepository.findByNameContainingIgnoreCase(name);
    }

    // Get a member by ID
    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    // Update an existing member
    public Member updateMember(Long id, Member updatedMember) {
        Member existingMember = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with ID: " + id));

        // Update fields
        existingMember.setName(updatedMember.getName());
        existingMember.setAddress(updatedMember.getAddress());
        existingMember.setEmail(updatedMember.getEmail());
        existingMember.setPhoneNumber(updatedMember.getPhoneNumber());
        existingMember.setMembershipStartDate(updatedMember.getMembershipStartDate());
        existingMember.setMembershipDuration(updatedMember.getMembershipDuration());

        return memberRepository.save(existingMember);
    }

    // Delete a member by ID
    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new IllegalArgumentException("Member not found with ID: " + id);
        }
        memberRepository.deleteById(id);
    }
}
