package me.emate.matefront.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import me.emate.matefront.category.dto.CategoryListResponseDto;
import me.emate.matefront.category.service.CategoryService;
import me.emate.matefront.tag.dto.TagListResponseDto;
import me.emate.matefront.tag.service.TagService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Utils {
    private final TagService tagService;
    private final CategoryService categoryService;

    public static HttpHeaders makeHeader() {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        return headers;
    }

    public void sidebarInModel(Model model) {
        List<CategoryListResponseDto> categoryListResponseDtoList = categoryService.getAllCategories();
        List<TagListResponseDto> tagListResponseDtoList = tagService.getAllTags();

        model.addAttribute("categories", categoryListResponseDtoList);
        model.addAttribute("tags", tagListResponseDtoList);
    }
}
