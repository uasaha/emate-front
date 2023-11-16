package me.emate.matefront.config;

import lombok.RequiredArgsConstructor;
import me.emate.matefront.visitor.interceptor.VisitorInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

  private final VisitorInterceptor visitorInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(visitorInterceptor)
        .addPathPatterns("/*")
        .excludePathPatterns("/logout", "/robots.txt", "/sitemap.xml");
  }
}
