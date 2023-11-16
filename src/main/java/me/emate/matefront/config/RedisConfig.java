package me.emate.matefront.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
@Configuration
public class RedisConfig {

  @Value("${emate.redis.host}")
  private String host;

  @Value("${emate.redis.port}")
  private String port;

  @Value("${emate.redis.password}")
  private String password;

  @Bean
  public LettuceConnectionFactory redisConnectionFactory() {
    final LettuceClientConfiguration clientConfiguration =
        LettuceClientConfiguration.builder()
            .useSsl().build();

    RedisStandaloneConfiguration standaloneConfiguration =
        new RedisStandaloneConfiguration();

    standaloneConfiguration.setHostName(host);
    standaloneConfiguration.setPort(Integer.parseInt(port));
    standaloneConfiguration.setPassword(password);

    return new LettuceConnectionFactory(standaloneConfiguration, clientConfiguration);
  }

  @Bean
  public RedisTemplate<?, ?> redisTemplate() {
    RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory());
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
    redisTemplate.setDefaultSerializer(new StringRedisSerializer());

    return redisTemplate;
  }
}
