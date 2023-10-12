package me.emate.matefront.contents.adaptor;

import me.emate.matefront.contents.dto.ContentsDetailResponseDto;
import me.emate.matefront.contents.dto.ContentsListResponseDto;
import me.emate.matefront.contents.dto.CreateContentsRequestDto;
import me.emate.matefront.utils.PageableResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContentsAdaptor {
    ContentsDetailResponseDto requestContentsByNo(Integer contentsNo);

    ContentsDetailResponseDto registerContents(CreateContentsRequestDto requestDto);

    ContentsDetailResponseDto requestContentsBySubject(String subject);

    ContentsDetailResponseDto requestLatestContent();

    PageableResponse<ContentsListResponseDto> requestContentsByCategory(String category, Pageable pageable);

    List<ContentsListResponseDto> requestLatestContents();
}
