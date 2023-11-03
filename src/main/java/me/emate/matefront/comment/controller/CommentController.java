package me.emate.matefront.comment.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import me.emate.matefront.comment.dto.CommentNoMemberRegisterRequestDto;
import me.emate.matefront.comment.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final static String REFERER = "Referer";

    @PostMapping("/anonymous")
    public String registerNoMemberComment(
            HttpServletRequest request,
            @ModelAttribute CommentNoMemberRegisterRequestDto requestDto) {
        commentService.registerAnonymousComment(requestDto);

        String referer = request.getHeader(REFERER);
        return "redirect:"+ referer;
    }
}
