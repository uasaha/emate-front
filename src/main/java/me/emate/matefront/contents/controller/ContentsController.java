package me.emate.matefront.contents.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.matefront.contents.dto.ContentsDetailResponseDto;
import me.emate.matefront.contents.dto.ContentsListResponseDto;
import me.emate.matefront.contents.dto.CreateContentsRequestDto;
import me.emate.matefront.contents.service.ContentsService;
import me.emate.matefront.member.NotAuthorizedException;
import me.emate.matefront.utils.PageableResponse;
import me.emate.matefront.utils.Utils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

        return "redirect:/" + noBlankSubject;
    }

    @GetMapping("/{subject}")
    public String contentsDetailView(@PathVariable("subject") String subject,
                                     Model model) {

        ContentsDetailResponseDto responseDto =
                contentsService.viewContentsBySubject(subject);

        if(responseDto.isHidden() || responseDto.isDeleted()) {
            throw new NotAuthorizedException();
        }

        final int maxSize = 77;

        utils.sidebarInModel(model);
        utils.modelRequestMemberNo(model);
        model.addAttribute("content", responseDto);
        model.addAttribute("urlPath", responseDto.getSubject().replace(" ", "-"));

        if(responseDto.getDetail().length() > maxSize) {
            model.addAttribute("description", responseDto.getDetail().substring(0, maxSize));
        } else {
            model.addAttribute("description", responseDto.getDetail());
        }

        return "contents/detail-contents";
    }

    @GetMapping
    public String viewTotalContents(@PageableDefault(size = 8) Pageable pageable,
                                    Model model) {
        setContentsInModel(model, contentsService.requestContentsTotal(pageable));
        utils.sidebarInModel(model);
        utils.modelRequestMemberNo(model);

        return "contents/total-contents";
    }

    @GetMapping("/search")
    public String viewContentsSearch(@PageableDefault(size = 8) Pageable pageable,
                                     Model model,
                                     @RequestParam("key") String key) {
        setContentsInModel(model, contentsService.requestContentsContainsKeyword(key, pageable));
        model.addAttribute("keyword", key);
        utils.sidebarInModel(model);
        utils.modelRequestMemberNo(model);

        return "contents/contents-search";
    }

    public static void setContentsInModel(Model model, PageableResponse<ContentsListResponseDto> responses) {
        model.addAttribute("contents", responses.getContents());
        model.addAttribute("totalPage", responses.getTotalPages());
        model.addAttribute("current", responses.getCurrent());
        model.addAttribute("hasNext", responses.isHasNext());
        model.addAttribute("hasPrevious", responses.isHasPrevious());
        model.addAttribute("pageButton", 5);
    }
}