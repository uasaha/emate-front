package me.emate.matefront.token.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.matefront.member.dto.MemberDetailResponseDto;
import me.emate.matefront.token.dto.AuthDto;
import me.emate.matefront.utils.CookieUtils;
import me.emate.matefront.utils.JwtUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static me.emate.matefront.utils.JwtUtils.AUTHENTICATION;
import static me.emate.matefront.utils.JwtUtils.SESSION_COOKIE;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {
    private final RedisTemplate<String, AuthDto> redisTemplate;
    private final ObjectMapper objectMapper;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) {
        try {
            if (notControllerUri(request)) {
                filterChain.doFilter(request, response);
                return;
            }

            Cookie sessionCookie = CookieUtils.findCookie(SESSION_COOKIE);
            if (notExistCookie(request, response, filterChain, sessionCookie)) {
                return;
            }

            Cookie jwtCookie = CookieUtils.findCookie(JwtUtils.JWT_COOKIE);
            if (notExistCookie(request, response, filterChain, jwtCookie)) {
                return;
            }

            String sessionId = Objects.requireNonNull(sessionCookie).getValue();

            MemberDetailResponseDto member = (MemberDetailResponseDto)
                    redisTemplate.opsForHash().get(AUTHENTICATION, sessionId);
            if (notExistLoginData(request, response, filterChain, member)) {
                return;
            }

            List<SimpleGrantedAuthority> authorities =
                    JwtUtils.makeAuthorities(Objects.requireNonNull(member).getAuthorities());

            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(new UsernamePasswordAuthenticationToken(
                    member.getMemberNo().toString(),
                    objectMapper.writeValueAsString(member),
                    authorities)
            );

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            SecurityContextHolder.clearContext();
        }
    }

    private static boolean notExistLoginData(HttpServletRequest request,
                                             HttpServletResponse response,
                                             FilterChain filterChain,
                                             MemberDetailResponseDto member)
            throws IOException, ServletException {

        if (Objects.isNull(member)) {
            filterChain.doFilter(request, response);
            return true;
        }
        return false;
    }

    private static boolean notExistCookie(HttpServletRequest request,
                                          HttpServletResponse response,
                                          FilterChain filterChain,
                                          Cookie cookie) throws IOException, ServletException {
        if (Objects.isNull(cookie)) {
            filterChain.doFilter(request, response);
            return true;
        }
        return false;
    }

    private static boolean notControllerUri(HttpServletRequest request) {
        return request.getRequestURI().contains("/static")
                || request.getRequestURI().equals("/error");
    }
}
