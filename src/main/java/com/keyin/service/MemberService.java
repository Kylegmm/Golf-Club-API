package com.keyin.service;

import com.keyin.model.Member;
import com.keyin.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> searchByName(String name) {
        return memberRepository.findByNameContaining(name);
    }
}
