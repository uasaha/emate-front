package me.emate.matefront.contents.adaptor;

import static me.emate.matefront.utils.Utils.makeHeader;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.matefront.config.ToBackConfig;
import me.emate.matefront.contents.dto.ContentsDetailResponseDto;
import me.emate.matefront.contents.dto.ContentsListResponseDto;
import me.emate.matefront.contents.dto.CreateContentsRequestDto;
import me.emate.matefront.utils.PageableResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class ContentsAdaptorImpl implements ContentsAdaptor {

  private final RestTemplate restTemplate;
  private final ToBackConfig toBackConfig;
  private static final String CONTENTS_URL = "/contents";
  private static final String CATEGORY_URL = "/category";
  private static final String TAG_URL = "/tags";

  @Override
  public ContentsDetailResponseDto requestContentsByNo(Integer contentsNo) {
    return restTemplate.exchange(
        toBackConfig.getBackUrl() + CONTENTS_URL + "/" + contentsNo,
        HttpMethod.GET,
        new HttpEntity<>(makeHeader()),
        ContentsDetailResponseDto.class).getBody();
  }

  @Override
  public ContentsDetailResponseDto registerContents(CreateContentsRequestDto requestDto) {
    return restTemplate.exchange(
        toBackConfig.getBackUrl() + CONTENTS_URL,
        HttpMethod.POST,
        new HttpEntity<>(requestDto, makeHeader()),
        ContentsDetailResponseDto.class).getBody();
  }

  @Override
  public ContentsDetailResponseDto requestContentsBySubject(String subject) {
    return restTemplate.exchange(
        toBackConfig.getBackUrl() + CONTENTS_URL + "/" + subject,
        HttpMethod.GET,
        new HttpEntity<>(makeHeader()),
        ContentsDetailResponseDto.class).getBody();
  }

  @Override
  public ContentsDetailResponseDto requestLatestContent() {
    HttpHeaders headers = makeHeader();

    return restTemplate.exchange(
        toBackConfig.getBackUrl() + CONTENTS_URL + "/latest",
        HttpMethod.GET,
        new HttpEntity<>(headers),
        ContentsDetailResponseDto.class).getBody();
  }

  @Override
  public PageableResponse<ContentsListResponseDto> requestContentsByTag(String tag,
      Pageable pageable) {
    String url = UriComponentsBuilder
        .fromHttpUrl(toBackConfig.getBackUrl() + TAG_URL + "/" + tag)
        .queryParam("page", pageable.getPageNumber())
        .queryParam("size", 8L)
        .encode()
        .toUriString();

    return restTemplate.exchange(
        url,
        HttpMethod.GET,
        new HttpEntity<>(makeHeader()),
        new ParameterizedTypeReference<PageableResponse<ContentsListResponseDto>>() {
        }
    ).getBody();
  }

  @Override
  public PageableResponse<ContentsListResponseDto> requestContentsByCategory(String tag,
      Pageable pageable) {
    String url = UriComponentsBuilder
        .fromHttpUrl(toBackConfig.getBackUrl() + CATEGORY_URL + "/" + tag)
        .queryParam("page", pageable.getPageNumber())
        .queryParam("size", 8L)
        .encode()
        .toUriString();

    return restTemplate.exchange(
        url,
        HttpMethod.GET,
        new HttpEntity<>(makeHeader()),
        new ParameterizedTypeReference<PageableResponse<ContentsListResponseDto>>() {
        }
    ).getBody();
  }

  @Override
  public List<ContentsListResponseDto> requestLatestContents() {
    return restTemplate.exchange(
        toBackConfig.getBackUrl() + CONTENTS_URL + "/latests",
        HttpMethod.GET,
        new HttpEntity<>(makeHeader()),
        new ParameterizedTypeReference<List<ContentsListResponseDto>>() {
        }
    ).getBody();
  }

  @Override
  public PageableResponse<ContentsListResponseDto> requestContentsTotal(Pageable pageable) {
    String url = UriComponentsBuilder
        .fromHttpUrl(toBackConfig.getBackUrl() + CONTENTS_URL + "/totals")
        .queryParam("page", pageable.getPageNumber())
        .queryParam("size", 8L)
        .encode()
        .toUriString();

    return restTemplate.exchange(
        url,
        HttpMethod.GET,
        new HttpEntity<>(makeHeader()),
        new ParameterizedTypeReference<PageableResponse<ContentsListResponseDto>>() {
        }
    ).getBody();
  }

  @Override
  public PageableResponse<ContentsListResponseDto> requestContentsContainsKeyword(String key,
      Pageable pageable) {
    String url = UriComponentsBuilder
        .fromHttpUrl(toBackConfig.getBackUrl() + CONTENTS_URL + "/search")
        .queryParam("key", key)
        .queryParam("page", pageable.getPageNumber())
        .queryParam("size", 8L)
        .encode()
        .toUriString();

    return restTemplate.exchange(
        url,
        HttpMethod.GET,
        new HttpEntity<>(makeHeader()),
        new ParameterizedTypeReference<PageableResponse<ContentsListResponseDto>>() {
        }
    ).getBody();
  }
}
