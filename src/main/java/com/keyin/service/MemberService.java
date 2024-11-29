package com.keyin.service;

import com.keyin.model.Member;
import com.keyin.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository; // Ensure this field is properly annotated

    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElse(null); // Returns null if not found
    }

    public Member updateMember(Long id, Member updatedMember) {
        return memberRepository.findById(id)
                .map(existingMember -> {
                    existingMember.setName(updatedMember.getName());
                    existingMember.setAddress(updatedMember.getAddress());
                    existingMember.setEmail(updatedMember.getEmail());
                    existingMember.setPhoneNumber(updatedMember.getPhoneNumber());
                    existingMember.setMembershipStartDate(updatedMember.getMembershipStartDate());
                    existingMember.setMembershipDuration(updatedMember.getMembershipDuration());
                    return memberRepository.save(existingMember);
                })
                .orElse(null);
    }

    public boolean deleteMember(Long id) {
        if (memberRepository.existsById(id)) {
            memberRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Member> searchByName(String name) {
        return memberRepository.findByNameContainingIgnoreCase(name);
    }
}
