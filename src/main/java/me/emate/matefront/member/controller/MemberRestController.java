package me.emate.matefront.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.matefront.member.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberRestController {
    private final MemberService memberService;

    @PostMapping("/idcheck")
    public boolean idConflictCheck(@RequestBody String id) {
        return memberService.idConflictCheck(id);
    }

    @PostMapping("/nickcheck")
    public boolean nickConflictCheck(@RequestBody String nickname) {
        return memberService.isNickConflict(nickname);
    }

    @PostMapping("emailcheck")
    public boolean emailConflictCheck(@RequestBody String email) {
        return memberService.isEmailConflict(email);
    }
}
