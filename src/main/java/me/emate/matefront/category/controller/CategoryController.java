package me.emate.matefront.category.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final ContentsService contentsService;
    private final Utils utils;

    @GetMapping("/{category}")
    public String viewContentsByCategory(@PathVariable String category,
                                         @PageableDefault(size = 8) Pageable pageable,
                                         Model model) {
        setContentsInModel(model, contentsService.requestContentsByCategory(category, pageable));
        model.addAttribute("categoryName", category);
        utils.sidebarInModel(model);
        utils.modelRequestMemberNo(model);

        return "contents/list-contents";
    }
}
