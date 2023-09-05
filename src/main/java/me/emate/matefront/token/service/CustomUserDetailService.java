package me.emate.matefront.token.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.matefront.member.MemberNotFountException;
import me.emate.matefront.member.adaptor.MemberAdaptor;
import me.emate.matefront.member.dto.MemberDetailResponseDto;
import me.emate.matefront.token.dto.AuthDto;
import me.emate.matefront.utils.CookieUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Objects;

import static me.emate.matefront.utils.JwtUtils.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final MemberAdaptor memberAdaptor;
    private final RedisTemplate<String, AuthDto> redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String accessToken) throws UsernameNotFoundException {
        ServletRequestAttributes servletRequestAttributes
                = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();

        String sessionId = request.getSession().getId();

        CookieUtils.makeCookie(response, SESSION_COOKIE, sessionId);

        MemberDetailResponseDto member
                = memberAdaptor.requestAuthMemberInfo(accessToken);

        if (Objects.isNull(member)) {
            throw new MemberNotFountException();
        }

        List<SimpleGrantedAuthority> authorities =
                makeAuthorities(member.getAuthorities());

        redisTemplate.opsForHash().put(AUTHENTICATION, sessionId, member);

        return new User(member.getMemberNo().toString(),
                "dummy",
                authorities);
    }
}
