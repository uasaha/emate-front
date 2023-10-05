package me.emate.matefront.main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class MainRestController {
    @GetMapping("/dGltZXpvbmU=")
    public String getTime() {
        return LocalDateTime.now().toString();
    }
}
