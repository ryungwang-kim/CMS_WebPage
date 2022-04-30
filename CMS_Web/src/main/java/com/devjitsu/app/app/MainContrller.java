package com.devjitsu.app.app;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devjitsu.app.app.log.dto.ConnectLogDTO;
import com.devjitsu.app.app.log.repository.ConnectLogRepository;
import com.devjitsu.app.app.member.repository.MemberRepository;
import com.devjitsu.app.config.Sha256Config;
import com.devjitsu.app.model.DConnectLog;
import com.devjitsu.app.model.DMemInfo;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class MainContrller {
	
	private final MemberRepository memberRepository;

	private final ConnectLogRepository connectLogRepository;
	
	@GetMapping("/")
    public String main(){
        return "main";
    }
	
	@GetMapping("/user/login")
	public String login() {
		
		return "/user/login";
	}
	
	@ResponseBody
	@RequestMapping(value="/user/loginAction")
	public Map<String,Object> web_login(@RequestParam Map<String, Object> param, HttpSession session, HttpServletRequest request) throws Exception {
		DMemInfo memInfo = memberRepository.findByMemId(param.get("memId").toString());
		Map<String, Object> resultMap = new HashMap<String, Object>();

		boolean chk = true;
		String message = "";
		String passWordCheck = checkPassword(param);
		
		if(memInfo == null) {
			 chk = false;
			 message = "사용자 정보가 없습니다.";
		}else if(!passWordCheck.equals(memInfo.getMemPw())){
            chk = false;
            message = "비밀번호가 일치하지 않습니다.";
        }
		
		if(chk == true) {
			ConnectLogDTO connectLogDTO = new ConnectLogDTO();
			connectLogDTO.setMemNo(memInfo.getMemNo());
			connectLogDTO.setMemId(memInfo.getMemNm()+" ["+memInfo.getMemId()+"]");
			connectLogDTO.setIp(request.getRemoteAddr());
			connectLogDTO.setLogType("LOGIN");
			DConnectLog dConnectLog = new DConnectLog(connectLogDTO);
			connectLogRepository.save(dConnectLog);
			
			session.setAttribute("memId", memInfo.getMemId());
		}
		
		resultMap.put("check", chk);
		resultMap.put("message", message);
		
		return resultMap;
    }
	@RequestMapping("/user/logout")
	public String logout(@RequestParam Map<String, Object> param, HttpSession session, HttpServletRequest request) {
		DMemInfo memInfo = memberRepository.findByMemId(session.getAttribute("memId").toString());
		ConnectLogDTO connectLogDTO = new ConnectLogDTO();
		connectLogDTO.setMemNo(memInfo.getMemNo());
		connectLogDTO.setMemId(memInfo.getMemNm()+" ["+memInfo.getMemId()+"]");
		connectLogDTO.setIp(request.getRemoteAddr());
		connectLogDTO.setLogType("LOGOUT");
		DConnectLog dConnectLog = new DConnectLog(connectLogDTO);
		connectLogRepository.save(dConnectLog);
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	//비밀번호 체크
	public String checkPassword(Map<String, Object> param) throws Exception {
		// sha256 암호비교
		Sha256Config sha256Config = new Sha256Config();
		String pwCheck = sha256Config.SHA256Encrypt(param.get("memPw").toString());
		
		return pwCheck;
	}

}
