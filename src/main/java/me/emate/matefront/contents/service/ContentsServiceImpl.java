package me.emate.matefront.contents.service;

import lombok.RequiredArgsConstructor;
import me.emate.matefront.contents.adaptor.ContentsAdaptor;
import me.emate.matefront.contents.dto.ContentsDetailResponseDto;
import me.emate.matefront.contents.dto.CreateContentsRequestDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentsServiceImpl implements ContentsService {
    private final ContentsAdaptor contentsAdaptor;

    @Override
    public ContentsDetailResponseDto registerContents(CreateContentsRequestDto requestDto) {
        return contentsAdaptor.registerContents(requestDto);
    }

    @Override
    public ContentsDetailResponseDto viewContentsByNo(Integer contentsNo) {
        return contentsAdaptor.requestContentsByNo(contentsNo);
    }
}
