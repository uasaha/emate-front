package me.emate.matefront.visitor.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.matefront.config.ToBackConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;

import static me.emate.matefront.visitor.interceptor.VisitorInterceptor.VISITOR_KEY;

@Slf4j
@Component
@RequiredArgsConstructor
public class VisitorScheduler {
    private final RedisTemplate<String, String> redisTemplate;
    private final ToBackConfig toBackConfig;
    private final RestTemplate restTemplate;
    private static final String VISITOR_URL = "/visitor";
    private static final String TODAY_KEY = "today";

    @Scheduled(cron = "55 59 23 1/1 * ?", zone = "Asia/Seoul")
    @CacheEvict(value = "total-visitor", allEntries = true)
    public void setTodayVisitorOnBack() {
        log.info("====scheduled====");
        Integer today = Integer.parseInt(
                Objects.requireNonNull(
                        redisTemplate.opsForValue().get(TODAY_KEY)));

        setTodayVisitorToTotal(today);
        redisTemplate.delete(TODAY_KEY);
        redisTemplate.opsForValue().set(TODAY_KEY, String.valueOf(0));
        redisTemplate.delete(VISITOR_KEY);
    }

    private void setTodayVisitorToTotal(Integer todayVisitor) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        String url = UriComponentsBuilder
                .fromHttpUrl(toBackConfig.getBackUrl() + VISITOR_URL + "/" + todayVisitor)
                .encode()
                .toUriString();

        restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<Void>() {});
    }
}
