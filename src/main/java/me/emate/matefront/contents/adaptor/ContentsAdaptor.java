package me.emate.matefront.contents.adaptor;

import me.emate.matefront.contents.dto.ContentsDetailResponseDto;
import me.emate.matefront.contents.dto.CreateContentsRequestDto;

public interface ContentsAdaptor {
    ContentsDetailResponseDto requestContentsByNo(Integer contentsNo);

    ContentsDetailResponseDto registerContents(CreateContentsRequestDto requestDto);
}
