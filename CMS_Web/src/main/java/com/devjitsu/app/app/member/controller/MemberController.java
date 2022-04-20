package com.devjitsu.app.app.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member") // URL 경로
public class MemberController {
	
	@GetMapping("/memberList")
	public String memberList(HttpSession join_, Model model) {
		
		return "/member/memberList";
    }
}
