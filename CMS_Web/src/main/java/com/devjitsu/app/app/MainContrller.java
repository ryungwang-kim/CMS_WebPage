package com.devjitsu.app.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class MainContrller {
	
	@GetMapping("/")
    public String main(){
        return "main";
    }
	
	@GetMapping("/user/login.do")
	public String login() {
		
		return "/user/login"; // 파일경로
	}
	
}
