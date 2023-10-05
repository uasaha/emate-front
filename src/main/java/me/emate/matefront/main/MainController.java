package me.emate.matefront.main;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.emate.matefront.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {
    private final Utils utils;

    @GetMapping
    public String mainView(HttpServletResponse response,
                           Model model) {
        utils.sidebarInModel(model);
        utils.modelRequestMemberNo(model);

        return "main/main";
    }

    @GetMapping("/dGltZXpvbmU=")
    public String getTime() {
        return LocalDateTime.now().toString();
    }
}
