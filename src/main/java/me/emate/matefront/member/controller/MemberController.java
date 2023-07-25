package me.emate.matefront.member.controller;

import lombok.RequiredArgsConstructor;
import me.emate.matefront.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final Utils utils;

    @GetMapping("/login")
    public String loginView(Model model) {
        utils.sidebarInModel(model);

        return "member/login-page";
    }
}
