package me.emate.matefront.token.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.matefront.member.adaptor.MemberAdaptor;
import me.emate.matefront.token.exception.NotLoginException;
import me.emate.matefront.utils.CookieUtils;
import me.emate.matefront.utils.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static me.emate.matefront.utils.JwtUtils.JWT_COOKIE;

@Component
@Slf4j
@RequiredArgsConstructor
public class TokenInterceptor implements HandlerInterceptor {
    private static final Long RENEW_TIME = Duration.ofHours(1).toSeconds();
    private static final String ERROR_MESSAGE = "X-MESSAGE";
    private final MemberAdaptor memberAdaptor;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws IOException {

        Cookie jwtCookie = CookieUtils.findCookie(JWT_COOKIE);

        if (unauthorizedAccess(jwtCookie)) {
            return true;
        }

        String cookieValue = Objects.requireNonNull(jwtCookie).getValue();
        String exp = cookieValue.split("\\.")[3];
        String accessToken =
                jwtCookie.getValue().substring(0, accessTokenLength(cookieValue, exp));

        long validTime = getValidTime(exp);

        if (validTime <= RENEW_TIME) {
            ResponseEntity<Void> result = memberAdaptor.tokenReIssueRequest(accessToken);
            List<String> messages = result.getHeaders().get(ERROR_MESSAGE);

            if (isRefreshTokenExpired(response, messages)) {
                log.warn("refreshToken expired");
                throw new NotLoginException();
            }

            return renewAccessToken(request, response, jwtCookie, result);
        }

        requestContainAccessToken(request, accessToken);
        return true;
    }

    private static int accessTokenLength(String cookieValue, String exp) {
        return cookieValue.length() - (exp.length() + 1);
    }

    private static boolean renewAccessToken(HttpServletRequest request,
                                            HttpServletResponse response,
                                            Cookie jwtCookie, ResponseEntity<Void> result) {
        String renewAccessToken = getRenewAccessToken(result);
        Long expireTime = getExpireTime(result);

        updateAccessToken(response, jwtCookie, renewAccessToken, expireTime);
        requestContainAccessToken(request, renewAccessToken);

        return true;
    }

    private static boolean unauthorizedAccess(Cookie jwtCookie) {
        return Objects.isNull(jwtCookie);
    }

    private static boolean isRefreshTokenExpired(HttpServletResponse response,
                                                 List<String> messages) throws IOException {
        if (Objects.nonNull(messages)) {
            response.sendError(401, messages.get(0));
            return true;
        }
        return false;
    }

    private static String getRenewAccessToken(ResponseEntity<Void> result) {
        return Objects.requireNonNull(result.getHeaders().get(JwtUtils.AUTH_HEADER))
                .get(0).substring(JwtUtils.TOKEN_TYPE.length());
    }

    private static long getExpireTime(ResponseEntity<Void> result) {
        return Long.parseLong(
                Objects.requireNonNull(
                        result.getHeaders().get(JwtUtils.EXP_HEADER)).get(0)
        );
    }

    private static void updateAccessToken(HttpServletResponse response, Cookie jwtCookie,
                                          String renewAccessToken, Long expireTime) {
        jwtCookie.setValue(renewAccessToken + "." + expireTime);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(7200);
        response.addCookie(jwtCookie);
    }

    private static long getValidTime(String exp) {
        long now = new Date().getTime();
        return (Long.parseLong(exp) - now) / JwtUtils.MILL_SEC;
    }

    private static void requestContainAccessToken(HttpServletRequest request, String accessToken) {
        request.setAttribute(JwtUtils.AUTH_HEADER, JwtUtils.TOKEN_TYPE + accessToken);
    }
}
