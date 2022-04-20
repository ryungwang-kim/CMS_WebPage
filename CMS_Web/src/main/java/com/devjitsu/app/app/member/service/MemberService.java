package com.devjitsu.app.app.member.service;

import org.springframework.stereotype.Service;

import com.devjitsu.app.app.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    
    private final MemberRepository memberRepository;


    public boolean existsByMemId(String memId) {
        return memberRepository.existsByMemId(memId);
    }
    
}
