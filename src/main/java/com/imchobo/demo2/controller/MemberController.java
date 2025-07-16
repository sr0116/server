package com.imchobo.demo2.controller;

import com.imchobo.demo2.domain.Member;
import com.imchobo.demo2.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("member")
@AllArgsConstructor
public class MemberController {
  private MemberService memberService;


  @GetMapping("login")
  public void loginForm() {
  }
  @GetMapping("register")
  public void register() {
  }

  @PostMapping("login")
  public String login(Member member, HttpSession session, RedirectAttributes redirectAttributes) {
    log.info("{}", member);
    if (memberService.login(member.getId(), member.getPw())) {
      session.setAttribute("member", memberService.findByID(member.getId()));
      return "redirect:/";
    }
    redirectAttributes.addFlashAttribute("msg", "로그인 실패");
    return "redirect:/member/login";
  }

  @RequestMapping(value = "logout", method = {RequestMethod.GET, RequestMethod.POST})
  public String login(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }

    @PostMapping("register")
    public String register(Member member) {
      memberService.register(member);
      return "redirect:/";
    }
  }




