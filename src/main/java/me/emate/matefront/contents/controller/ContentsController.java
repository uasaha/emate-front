package me.emate.matefront.contents.controller;

import lombok.RequiredArgsConstructor;
import me.emate.matefront.contents.service.ContentsService;
import me.emate.matefront.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/content")
public class ContentsController {
    private final Utils utils;
    private final ContentsService contentsService;

    @GetMapping("/register")
    public String registerContentsView(Model model) {
        utils.sidebarInModel(model);

        return "/contents/register-contents";
    }
}