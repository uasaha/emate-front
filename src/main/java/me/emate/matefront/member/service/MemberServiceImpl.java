package me.emate.matefront.member.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.matefront.member.adaptor.MemberAdaptor;
import me.emate.matefront.member.dto.CheckEmailRequestDto;
import me.emate.matefront.member.dto.CheckIDRequestDto;
import me.emate.matefront.member.dto.CheckNicknameRequestDto;
import me.emate.matefront.member.dto.SignupRequestDto;
import me.emate.matefront.token.dto.AuthDto;
import me.emate.matefront.utils.CookieUtils;
import me.emate.matefront.utils.JwtUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static me.emate.matefront.utils.JwtUtils.AUTHENTICATION;
import static me.emate.matefront.utils.JwtUtils.SESSION_COOKIE;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberAdaptor memberAdaptor;
    private final PasswordEncoder encoder;
    private final RedisTemplate<String, AuthDto> redisTemplate;

    @Override
    public boolean idConflictCheck(String id) {
        CheckIDRequestDto requestDto = new CheckIDRequestDto(id);
        boolean a = Boolean.TRUE.equals(memberAdaptor.idConflictCheck(requestDto).getBody());
        return a;
    }

    @Override
    public boolean isNickConflict(String nickname) {
        CheckNicknameRequestDto requestDto = new CheckNicknameRequestDto(nickname);
        return Boolean.TRUE.equals(memberAdaptor.nickConflictCheck(requestDto).getBody());
    }

    @Override
    public boolean isEmailConflict(String email) {
        CheckEmailRequestDto requestDto = new CheckEmailRequestDto(email);
        return Boolean.TRUE.equals(memberAdaptor.emailConflictCheck(requestDto).getBody());
    }

    @Override
    public void signup(@Valid SignupRequestDto requestDto) {
        String encoded = encoder.encode(requestDto.getPwd());
        requestDto.setPwd(encoded);

        memberAdaptor.signup(requestDto);
    }

    @Override
    public void logout(HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.nonNull(authentication)) {
            Cookie jwtCookie = CookieUtils.findCookie(JwtUtils.JWT_COOKIE);
            if (Objects.isNull(jwtCookie)) {
                SecurityContextHolder.clearContext();
                return;
            }

            Cookie sessionCookie = CookieUtils.findCookie(SESSION_COOKIE);
            jwtCookie.setMaxAge(0);
            jwtCookie.setValue("");
            sessionCookie.setMaxAge(0);
            sessionCookie.setValue("");

            redisTemplate.opsForHash().delete(AUTHENTICATION, SESSION_COOKIE);

            response.addCookie(jwtCookie);
            response.addCookie(sessionCookie);
            SecurityContextHolder.clearContext();

            memberAdaptor.logout();
        }
    }
}
