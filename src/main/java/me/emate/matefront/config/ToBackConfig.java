package me.emate.matefront.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToBackConfig {

  @Value("${emate.back.url}")
  private String backUrl;

  public String getBackUrl() {
    return this.backUrl;
  }
}
