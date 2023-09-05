package me.emate.matefront.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import me.emate.matefront.category.dto.CategoryListResponseDto;
import me.emate.matefront.category.service.CategoryService;
import me.emate.matefront.member.dto.MemberDetailResponseDto;
import me.emate.matefront.tag.dto.TagListResponseDto;
import me.emate.matefront.tag.service.TagService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class Utils {
    private final TagService tagService;
    private final CategoryService categoryService;
    private final ObjectMapper objectMapper;

    public static HttpHeaders makeHeader() {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        String accessToken = (String) request.getAttribute(JwtUtils.AUTH_HEADER);
        if (Objects.nonNull(accessToken)) {
            headers.add(JwtUtils.AUTH_HEADER, accessToken);
        }

        return headers;
    }

    public void sidebarInModel(Model model) {
        List<CategoryListResponseDto> categoryListResponseDtoList = categoryService.getAllCategories();
        List<TagListResponseDto> tagListResponseDtoList = tagService.getAllTags();

        model.addAttribute("categories", categoryListResponseDtoList);
        model.addAttribute("tags", tagListResponseDtoList);
    }

    public void modelRequestMemberNo(Model model) {
        String credential =
                (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();

        MemberDetailResponseDto member;

        try {
            member = objectMapper.readValue(credential, MemberDetailResponseDto.class);
        } catch (JsonProcessingException e) {
            member = null;
        }

        model.addAttribute("memberNo", getMemberNo());
        model.addAttribute("member", member);
    }

    public Long getMemberNo() {
        String principal =
                (String) SecurityContextHolder.getContext()
                        .getAuthentication().getPrincipal();

        long memberNo = -1L;

        if (!principal.equals("anonymousUser")) {
            memberNo = Long.parseLong(principal);
        }

        return memberNo;
    }
}
