package me.emate.matefront.token.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.matefront.member.adaptor.MemberAdaptor;
import me.emate.matefront.member.dto.MemberLoginRequestDto;
import me.emate.matefront.utils.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Objects;

import static me.emate.matefront.utils.JwtUtils.makeJwtCookie;

@Slf4j
@RequiredArgsConstructor
public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {
    private final MemberAdaptor memberAdaptor;

    private static final String LOGIN_STATUS = "X-LOGIN";

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String id = obtainUsername(request);
        String password = obtainPassword(request);

        MemberLoginRequestDto loginMemberRequestDto = new MemberLoginRequestDto(id, password);

        ResponseEntity<Void> jwtResponse
                = memberAdaptor.loginRequest(loginMemberRequestDto);

        Long expireTime = getExpireTime(jwtResponse);
        String accessToken = getAccessToken(jwtResponse);

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(accessToken, password);

        Cookie cookie = makeJwtCookie(accessToken, expireTime);

        response.addCookie(cookie);

        return getAuthenticationManager().authenticate(token);
    }

    private static String getAccessToken(ResponseEntity<Void> jwtResponse) {
        return Objects.requireNonNull(jwtResponse.getHeaders()
                .get("Authorization")).get(0).substring(JwtUtils.TOKEN_TYPE.length());
    }

    private static Long getExpireTime(ResponseEntity<Void> jwtResponse) {
        return Long.parseLong(
                Objects.requireNonNull(
                        jwtResponse.getHeaders().get(JwtUtils.EXP_HEADER)).get(0));
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request, HttpServletResponse response,
            FilterChain chain, Authentication authResult) throws IOException {
        SecurityContextHolder.clearContext();
        response.setHeader(LOGIN_STATUS, "success");
    }
}
