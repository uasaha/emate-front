package me.emate.matefront.visitor.schedule;

import lombok.RequiredArgsConstructor;
import me.emate.matefront.visitor.service.VisitorService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import static me.emate.matefront.visitor.interceptor.VisitorInterceptor.TODAY_KEY;
import static me.emate.matefront.visitor.interceptor.VisitorInterceptor.VISITOR_KEY;

@RequiredArgsConstructor
public class VisitorScheduler {
    private final VisitorService visitorService;
    private final RedisTemplate<String, String> redisTemplate;

    @Scheduled(cron = "0 58 23 1/1 * ? *", zone = "Asia/Seoul")
    public void setTodayVisitorOnBack() {
        visitorService.setTodayVisitorToTotal();
        redisTemplate.delete(TODAY_KEY);
        redisTemplate.opsForValue().set(TODAY_KEY, String.valueOf(0));
        redisTemplate.delete(VISITOR_KEY);
    }
}
