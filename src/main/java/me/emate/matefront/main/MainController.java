package me.emate.matefront.main;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.emate.matefront.category.dto.CategoryListResponseDto;
import me.emate.matefront.category.service.CategoryService;
import me.emate.matefront.tag.dto.TagListResponseDto;
import me.emate.matefront.tag.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
    private final CategoryService categoryService;
    private final TagService tagService;

    @GetMapping
    public String mainView(HttpServletResponse response,
                           Model model) {
        List<CategoryListResponseDto> categoryListResponseDtoList = categoryService.getAllCategories();
        List<TagListResponseDto> tagListResponseDtoList = tagService.getAllTags();

        model.addAttribute("categories", categoryListResponseDtoList);
        model.addAttribute("tags", tagListResponseDtoList);

        return "main/main";
    }
}
