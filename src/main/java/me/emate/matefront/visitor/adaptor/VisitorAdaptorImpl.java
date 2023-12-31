package me.emate.matefront.visitor.adaptor;

import static me.emate.matefront.utils.Utils.makeHeader;

import lombok.RequiredArgsConstructor;
import me.emate.matefront.config.ToBackConfig;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequiredArgsConstructor
public class VisitorAdaptorImpl implements VisitorAdaptor {

  private final RestTemplate restTemplate;
  private final ToBackConfig toBackConfig;
  private static final String VISITOR_URL = "/visitors";

  @Override
  public Integer getTotalVisitor() {
    String url = UriComponentsBuilder
        .fromHttpUrl(toBackConfig.getBackUrl() + VISITOR_URL + "/total")
        .encode()
        .toUriString();

    return restTemplate.exchange(
        url,
        HttpMethod.GET,
        new HttpEntity<>(makeHeader()),
        new ParameterizedTypeReference<Integer>() {
        }).getBody();
  }

  @Override
  public void setTodayVisitorToTotal(Integer todayVisitor) {
    String url = UriComponentsBuilder
        .fromHttpUrl(toBackConfig.getBackUrl() + VISITOR_URL + "/" + todayVisitor)
        .encode()
        .toUriString();

    restTemplate.exchange(
        url,
        HttpMethod.POST,
        new HttpEntity<>(makeHeader()),
        new ParameterizedTypeReference<Void>() {
        });
  }
}
