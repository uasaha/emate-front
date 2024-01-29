package me.emate.matefront.visitor.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@RequiredArgsConstructor
@Component
public class VisitorInterceptor implements HandlerInterceptor {

  public static final String VISITOR_KEY = "visitor";
  public static final String TODAY_KEY = "today";
  private final RedisTemplate<String, String> redisTemplate;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    String ip = getClientIp(request);
    final int today = Integer.parseInt(
        Objects.requireNonNull(redisTemplate.opsForValue().get(TODAY_KEY)));

    if (ip.isEmpty()) {
      log.warn("IP 정보가 없습니다.");
      return true;
    }

    if (redisTemplate.opsForSet().members(VISITOR_KEY) != null) {
      Set<String> visitors = redisTemplate.opsForSet().members(VISITOR_KEY);
      assert visitors != null;

      if (visitors.contains(ip)) {
        return true;
      }
    }

    redisTemplate.opsForSet().add(VISITOR_KEY, ip);
    redisTemplate.opsForValue().set(TODAY_KEY, String.valueOf(today + 1));
    return true;
  }

  private String getClientIp(HttpServletRequest request) {
    String ip = request.getHeader("X-FORWARDED-FOR");

    if (ip == null) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null) {
      ip = request.getHeader("HTTP_CLIENT_IP");
    }
    if (ip == null) {
      ip = request.getHeader("HTTP_X_FORWARDED_FOR");
    }
    if (ip == null) {
      ip = request.getRemoteAddr();
    }

    return ip;
  }
}
