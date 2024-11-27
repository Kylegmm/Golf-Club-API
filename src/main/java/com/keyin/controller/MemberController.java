package com.keyin.controller;

import com.keyin.model.Member;
import com.keyin.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        return ResponseEntity.ok(memberService.addMember(member));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Member>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(memberService.searchByName(name));
    }
}
