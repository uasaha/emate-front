package me.emate.matefront.contents.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.matefront.comment.service.CommentService;
import me.emate.matefront.contents.dto.ContentsDetailResponseDto;
import me.emate.matefront.contents.dto.ContentsListResponseDto;
import me.emate.matefront.contents.dto.ContentsRegisterDto;
import me.emate.matefront.contents.dto.CreateContentsRequestDto;
import me.emate.matefront.contents.service.ContentsService;
import me.emate.matefront.member.NotAuthorizedException;
import me.emate.matefront.tag.dto.TagListResponseDto;
import me.emate.matefront.tag.service.TagService;
import me.emate.matefront.utils.PageableResponse;
import me.emate.matefront.utils.Utils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/contents")
public class ContentsController {
    private final Utils utils;
    private final ContentsService contentsService;
    private final TagService tagService;
    private final CommentService commentService;
    private static final String NUM_KOR_ENG = "[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9() ]";

    @GetMapping("/register")
    public String registerContentsView(Model model) {
        if(!utils.getMemberNo().equals(1)) {
            throw new NotAuthorizedException();
        }

        utils.sidebarInModel(model);
        utils.modelRequestMemberNo(model);
        model.addAttribute("tags", tagService.getAllTags());

        return "contents/register-contents";
    }

    @PostMapping("/register")
    public String registerContents(ContentsRegisterDto registerDto) {
        if(!utils.getMemberNo().equals(1)) {
            throw new NotAuthorizedException();
        }

        List<Integer> tags = Arrays
                .stream(registerDto.getTagNo().split(","))
                .map(Integer::parseInt).toList();

        log.info(registerDto.getHidden());

        contentsService.registerContents(
                new CreateContentsRequestDto(registerDto.getCategoryNo(),
                        tags,
                        registerDto.getHidden().equalsIgnoreCase("T"),
                        registerDto.getThumbnail(),
                        registerDto.getSubject(),
                        registerDto.getDetail()));

        return "redirect:/";
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
        model.addAttribute("urlPath",
                blankToHyphen(responseDto.getSubject()));

        for (TagListResponseDto tags : responseDto.getTags()) {
            tags.setTagUrl(blankToHyphen(tags.getTagName()));
        }

        model.addAttribute("description",
                getDescription(responseDto.getDetail()));

        model.addAttribute("comments",
                commentService.getCommentsByContentsNo(responseDto.getContentsNo()));

        return "contents/detail-contents";
    }

    private String blankToHyphen(String str) {
        return str.replace(" ", "-");
    }

    private String getDescription(String detail) {
        final int maxSize = 161;

        String description = detail
                .replaceAll(NUM_KOR_ENG, "")
                .replaceAll("br", "");

        if(description.length() > maxSize) {
            return description.substring(0, maxSize);
        }

        return description;
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
        setContentsInModel(model,
                contentsService.requestContentsContainsKeyword(key, pageable));
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