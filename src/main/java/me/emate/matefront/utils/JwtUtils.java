package me.emate.matefront.utils;

import jakarta.servlet.http.Cookie;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class JwtUtils {
    public static final String JWT_COOKIE = "emate_accessToken";
    public static final String AUTH_HEADER = "X-Authorization-Id";
    public static final String EXP_HEADER = "X-Expire";
    public static final String TOKEN_TYPE = "Bearer ";
    public static final Long MILL_SEC = 1000L;
    public static final Integer EXPIRE_TIME = 7200;
    public static final String AUTHENTICATION = "SPRING_SECURITY_CONTEXT";
    public static final String SESSION_COOKIE = "auth-session";
    public static final String DOMAIN = "domain";

    public static Cookie makeJwtCookie(String accessToken, Long expireTime) {
        String tokenInfo = accessToken + "." + expireTime;

        Cookie cookie = new Cookie(JWT_COOKIE, tokenInfo);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(EXPIRE_TIME);
        cookie.setPath("/");
        return cookie;
    }

    public static List<SimpleGrantedAuthority> makeAuthorities(List<String> roles) {
        return roles.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
