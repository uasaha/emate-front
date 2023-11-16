package me.emate.matefront.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.matefront.category.dto.CategoryListResponseDto;
import me.emate.matefront.category.service.CategoryService;
import me.emate.matefront.member.dto.MemberDetailResponseDto;
import me.emate.matefront.visitor.service.VisitorService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Slf4j
@RequiredArgsConstructor
public class Utils {

  private final CategoryService categoryService;
  private final VisitorService visitorService;
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
    Integer today = visitorService.getTodayVisitor();
    Integer total = visitorService.getTotalVisitor();

    model.addAttribute("today", today);
    model.addAttribute("total", total + today);
    model.addAttribute("categories", categoryListResponseDtoList);
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

  public Integer getMemberNo() {
    String principal =
        (String) SecurityContextHolder.getContext()
            .getAuthentication().getPrincipal();

    int memberNo = -1;

    if (!principal.equals("anonymousUser")) {
      memberNo = Integer.parseInt(principal);
    }

    return memberNo;
  }
}
