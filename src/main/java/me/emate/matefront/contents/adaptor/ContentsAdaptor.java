package me.emate.matefront.contents.adaptor;

import java.util.List;
import me.emate.matefront.contents.dto.ContentsDetailResponseDto;
import me.emate.matefront.contents.dto.ContentsListResponseDto;
import me.emate.matefront.contents.dto.CreateContentsRequestDto;
import me.emate.matefront.utils.PageableResponse;
import org.springframework.data.domain.Pageable;

public interface ContentsAdaptor {

  ContentsDetailResponseDto requestContentsByNo(Integer contentsNo);

  ContentsDetailResponseDto registerContents(CreateContentsRequestDto requestDto);

  ContentsDetailResponseDto requestContentsBySubject(String subject);

  ContentsDetailResponseDto requestLatestContent();

  PageableResponse<ContentsListResponseDto> requestContentsByTag(String tag, Pageable pageable);

  PageableResponse<ContentsListResponseDto> requestContentsByCategory(String category,
      Pageable pageable);

  List<ContentsListResponseDto> requestLatestContents();

  PageableResponse<ContentsListResponseDto> requestContentsTotal(Pageable pageable);

  PageableResponse<ContentsListResponseDto> requestContentsContainsKeyword(String key,
      Pageable pageable);
}
