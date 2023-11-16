package me.emate.matefront.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.matefront.member.dto.SignupRequestDto;
import me.emate.matefront.member.service.MemberService;
import me.emate.matefront.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

  private final Utils utils;
  private final MemberService memberService;
  private static final String REFERER = "Referer";

  @GetMapping("/login")
  public String loginView(Model model) {
    utils.sidebarInModel(model);
    utils.modelRequestMemberNo(model);

    return "member/login-page";
  }

  @GetMapping("/register")
  public String registerView(Model model) {
    utils.sidebarInModel(model);

    return "member/register-page";
  }

  @PostMapping("/register")
  public String signup(Model model,
      @ModelAttribute("signupForm") SignupRequestDto requestDto) {
    memberService.signup(requestDto);

    utils.sidebarInModel(model);
    return "member/register-success";
  }

  @GetMapping("/logout")
  public String logout(HttpServletRequest request,
      HttpServletResponse response) {
    memberService.logout(response);

    return "redirect:" + request.getHeader(REFERER);
  }
}
