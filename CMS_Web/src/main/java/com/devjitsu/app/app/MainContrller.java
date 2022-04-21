package com.devjitsu.app.app;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devjitsu.app.config.Sha256Config;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class MainContrller {
	
	@GetMapping("/")
    public String main(){
        return "main";
    }
	
	@GetMapping("/user/login")
	public String login() {
		
		return "/user/login";
	}
	
	@ResponseBody
	@RequestMapping(value="/user/loginAction.do")
	public Map<String,Object> web_login(@RequestParam Map<String, Object> param, HttpSession session, HttpServletRequest request) throws Exception {
		Map<String, Object> loginMap = new HashMap<String, Object>();;//loginService.selLoginMember(param);
		Map<String, Object> resultMap = new HashMap<String, Object>();

		boolean chk = true;
		String message = "";
		String passWordCheck = checkPassword(param);
		
		if(loginMap == null) {
			 chk = false;
			 message = "사용자 정보가 없습니다.";
		}else if(!passWordCheck.equals(loginMap.get("mem_pw"))){
            chk = false;
            message = "비밀번호가 일치하지 않습니다.";
        }
		
		if(chk == true) {
			Map<String, Object> djConnectMap = new HashMap<String, Object>();
			djConnectMap.put("memNo", loginMap.get("mem_no"));
			djConnectMap.put("memId", loginMap.get("mem_id"));
			djConnectMap.put("ip", request.getRemoteAddr());
			djConnectMap.put("logType", request.getMethod());
			djConnectMap.put("logTime", new Date());
			//loginService.insConnectLog(djConnectMap);
			
			session.setAttribute("memId", loginMap.get("mem_id"));
		}
		
		resultMap.put("check", chk);
		resultMap.put("message", message);
		
		return resultMap;
    }
	@RequestMapping("/user/logout.do")
	public String logout(@RequestParam Map<String, Object> param, HttpSession session, HttpServletRequest request) {
		Map<String, Object> logMap = new HashMap<String, Object>(); //loginService.selLoginMember(param);
		param.put("memNo", logMap.get("mem_no"));
		param.put("ip", request.getRemoteAddr());
		param.put("logType", "logout");
		param.put("logTime", new Date());
		//loginService.insConnectLog(param);
		
		session.invalidate();
		
		return "redirect:/index.do";
	}
	
	//비밀번호 체크
	public String checkPassword(Map<String, Object> param) throws Exception {
		// sha256 암호비교
		Sha256Config sha256Config = new Sha256Config();
		String pwCheck = sha256Config.SHA256Encrypt(param.get("memPw").toString());
		
		return pwCheck;
	}

}
