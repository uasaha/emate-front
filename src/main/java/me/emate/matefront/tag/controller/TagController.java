package me.emate.matefront.tag.controller;

import lombok.RequiredArgsConstructor;
import me.emate.matefront.contents.service.ContentsService;
import me.emate.matefront.utils.Utils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static me.emate.matefront.contents.controller.ContentsController.setContentsInModel;

@Controller
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {
    private final ContentsService contentsService;
    private final Utils utils;
    @GetMapping("/{tag}")
    public String viewContentsByCategory(@PathVariable String tag,
                                         @PageableDefault(size = 8) Pageable pageable,
                                         Model model) {
        setContentsInModel(model, contentsService.requestContentsByTag(tag, pageable));
        model.addAttribute("tagName", tag);
        model.addAttribute("tagUrl", tag.replace(" ", "-"));
        utils.sidebarInModel(model);
        utils.modelRequestMemberNo(model);

        return "contents/tag-list";
    }
}
