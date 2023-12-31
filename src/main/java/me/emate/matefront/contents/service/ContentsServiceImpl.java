package me.emate.matefront.contents.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.emate.matefront.contents.adaptor.ContentsAdaptor;
import me.emate.matefront.contents.dto.ContentsDetailResponseDto;
import me.emate.matefront.contents.dto.ContentsListResponseDto;
import me.emate.matefront.contents.dto.CreateContentsRequestDto;
import me.emate.matefront.utils.PageableResponse;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentsServiceImpl implements ContentsService {

  private final ContentsAdaptor contentsAdaptor;

  @CacheEvict(value = {"categories", "mainContents"}, allEntries = true)
  @Override
  public ContentsDetailResponseDto registerContents(CreateContentsRequestDto requestDto) {
    return contentsAdaptor.registerContents(requestDto);
  }

  @Override
  public ContentsDetailResponseDto viewContentsByNo(Integer contentsNo) {
    return contentsAdaptor.requestContentsByNo(contentsNo);
  }

  @Override
  public ContentsDetailResponseDto viewContentsBySubject(String subject) {
    return contentsAdaptor.requestContentsBySubject(subject);
  }

  @Override
  public ContentsDetailResponseDto requestLatestContent() {
    return contentsAdaptor.requestLatestContent();
  }

  @Override
  public PageableResponse<ContentsListResponseDto> requestContentsByCategory(String category,
      Pageable pageable) {
    PageableResponse<ContentsListResponseDto> responses = contentsAdaptor.requestContentsByCategory(
        category, pageable);
    setUrlPath(responses.getContents());

    return responses;
  }

  @Override
  public PageableResponse<ContentsListResponseDto> requestContentsByTag(String tag,
      Pageable pageable) {
    PageableResponse<ContentsListResponseDto> responses = contentsAdaptor.requestContentsByTag(tag,
        pageable);
    setUrlPath(responses.getContents());

    return responses;
  }

  @Cacheable(value = "mainContents")
  @Override
  public List<ContentsListResponseDto> requestLatestContents() {
    List<ContentsListResponseDto> responses = contentsAdaptor.requestLatestContents();
    setUrlPath(responses);

    return responses;
  }

  @Override
  public PageableResponse<ContentsListResponseDto> requestContentsTotal(Pageable pageable) {
    PageableResponse<ContentsListResponseDto> responses = contentsAdaptor.requestContentsTotal(
        pageable);
    setUrlPath(responses.getContents());

    return responses;
  }

  @Override
  public PageableResponse<ContentsListResponseDto> requestContentsContainsKeyword(String key,
      Pageable pageable) {
    PageableResponse<ContentsListResponseDto> responses =
        contentsAdaptor.requestContentsContainsKeyword(key, pageable);
    setUrlPath(responses.getContents());

    return responses;
  }

  private static void setUrlPath(List<ContentsListResponseDto> responses) {
    for (ContentsListResponseDto response : responses) {
      response.setUrlPath(response.getSubject().replace(" ", "-"));
    }
  }
}
