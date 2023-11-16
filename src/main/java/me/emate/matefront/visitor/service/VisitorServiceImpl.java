package me.emate.matefront.visitor.service;

import static me.emate.matefront.visitor.interceptor.VisitorInterceptor.TODAY_KEY;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import me.emate.matefront.visitor.adaptor.VisitorAdaptor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitorServiceImpl implements VisitorService {

  private final VisitorAdaptor visitorAdaptor;
  private final RedisTemplate<String, String> redisTemplate;

  @Override
  public Integer getTodayVisitor() {
    return Integer.parseInt(Objects.requireNonNull(redisTemplate.opsForValue().get(TODAY_KEY)));
  }

  @Cacheable(value = "total-visitor")
  @Override
  public Integer getTotalVisitor() {
    return visitorAdaptor.getTotalVisitor();
  }

  @CacheEvict(value = "total-visitor", allEntries = true)
  @Override
  public void setTodayVisitorToTotal() {
    visitorAdaptor.setTodayVisitorToTotal(getTodayVisitor());
  }
}
