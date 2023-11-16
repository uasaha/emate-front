package me.emate.matefront.category.adaptor;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.matefront.category.dto.CategoryListResponseDto;
import me.emate.matefront.config.ToBackConfig;
import me.emate.matefront.utils.Utils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryAdaptorImpl implements CategoryAdaptor {

  private final RestTemplate restTemplate;
  private final ToBackConfig toBackConfig;
  private static final String CATEGORY_URL = "/category";

  @Override
  public List<CategoryListResponseDto> getAllCategories() {
    String url = UriComponentsBuilder
        .fromHttpUrl(toBackConfig.getBackUrl() + CATEGORY_URL)
        .encode()
        .toUriString();

    return restTemplate.exchange(
        url,
        HttpMethod.GET,
        new HttpEntity<>(Utils.makeHeader()),
        new ParameterizedTypeReference<List<CategoryListResponseDto>>() {
        }
    ).getBody();
  }
}
