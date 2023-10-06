package me.emate.matefront.contents.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.matefront.contents.dto.ContentsDetailResponseDto;
import me.emate.matefront.contents.dto.CreateContentsRequestDto;
import me.emate.matefront.contents.service.ContentsService;
import me.emate.matefront.member.NotAuthorizedException;
import me.emate.matefront.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/contents")
public class ContentsController {
    private final Utils utils;
    private final ContentsService contentsService;

    @GetMapping("/register")
    public String registerContentsView(Model model) {
        if(!utils.getMemberNo().equals(1)) {
            throw new NotAuthorizedException();
        }

        utils.sidebarInModel(model);
        utils.modelRequestMemberNo(model);

        return "contents/register-contents";
    }

    @PostMapping("/register")
    public String registerContents(CreateContentsRequestDto requestDto) {
        if(!utils.getMemberNo().equals(1)) {
            throw new NotAuthorizedException();
        }

        ContentsDetailResponseDto responseDto =
                contentsService.registerContents(requestDto);

        String noBlankSubject = responseDto.getSubject().replace(" ", "-");

        return "redirect:/contents/" + noBlankSubject;
    }

    @GetMapping("/{subject}")
    public String contentsDetailView(@PathVariable("subject") String subject,
                                     Model model) {
        ContentsDetailResponseDto responseDto =
                contentsService.viewContentsBySubject(subject);

        if(responseDto.isHidden() || responseDto.isDeleted()) {
            throw new NotAuthorizedException();
        }

        utils.sidebarInModel(model);
        utils.modelRequestMemberNo(model);
        model.addAttribute("content", responseDto);

        return "contents/detail-contents";
    }
}