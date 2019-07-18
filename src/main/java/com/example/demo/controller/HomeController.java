package com.example.demo.controller;



import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;



@Controller
@Slf4j
public class HomeController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HomeController.class);

    private final MemberService memberService;

    public HomeController(MemberService memberService){
        this.memberService = memberService;
    }


    @GetMapping("/")
    public String home(){
        return "index";
    }

    @PostMapping("/join")
    public String join(Member member){
        memberService.join(member);
        log.info(member.toString() + " 의 회원가입 완료");
        
        return "index";
    }
}