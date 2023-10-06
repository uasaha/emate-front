package me.emate.matefront.contents.service;

import me.emate.matefront.contents.dto.ContentsDetailResponseDto;
import me.emate.matefront.contents.dto.CreateContentsRequestDto;

public interface ContentsService {
    ContentsDetailResponseDto registerContents(CreateContentsRequestDto requestDto);

    ContentsDetailResponseDto viewContentsByNo(Integer contentsNo);

    ContentsDetailResponseDto viewContentsBySubject(String subject);

    ContentsDetailResponseDto viewLatestContents();
}
