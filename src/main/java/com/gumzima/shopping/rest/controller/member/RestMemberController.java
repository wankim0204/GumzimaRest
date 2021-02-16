package com.gumzima.shopping.rest.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gumzima.shopping.model.common.MessageData;
import com.gumzima.shopping.model.domain.Member;
import com.gumzima.shopping.model.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RestMemberController {
	
	@Autowired
	MemberService memberService;
	
	// id 중복체크
	@GetMapping(value="/rest/idCheck/{m_id}")
	public MessageData Id_Check(@PathVariable String m_id) {	
		int result = memberService.Id_Check(m_id);
		log.debug("아이디체크요"+result);
		MessageData data = new MessageData();
		data.setResultCode(result);
		data.setMsg(m_id);
		data.setUrl(m_id);
		return data;
	}

	// 회원가입
	@PostMapping(value="/rest/regist")	
	public void regist(@RequestBody Member member) {
		log.debug("member테스트"+member.getM_pass());
		member.setM_email_server("naver.com");
		member.setM_addr3("123");
		memberService.regist(member);		
		
	}
}
