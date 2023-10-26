package me.emate.matefront.contents.service;

import me.emate.matefront.contents.dto.ContentsDetailResponseDto;
import me.emate.matefront.contents.dto.ContentsListResponseDto;
import me.emate.matefront.contents.dto.CreateContentsRequestDto;
import me.emate.matefront.utils.PageableResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContentsService {
    ContentsDetailResponseDto registerContents(CreateContentsRequestDto requestDto);

    ContentsDetailResponseDto viewContentsByNo(Integer contentsNo);

    ContentsDetailResponseDto viewContentsBySubject(String subject);

    ContentsDetailResponseDto requestLatestContent();

    PageableResponse<ContentsListResponseDto> requestContentsByCategory(String category, Pageable pageable);

    PageableResponse<ContentsListResponseDto> requestContentsByTag(String tag, Pageable pageable);

    List<ContentsListResponseDto> requestLatestContents();

    PageableResponse<ContentsListResponseDto> requestContentsTotal(Pageable pageable);

    PageableResponse<ContentsListResponseDto> requestContentsContainsKeyword(String key, Pageable pageable);
}
