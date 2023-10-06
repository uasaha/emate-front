package me.emate.matefront.contents.service;

import lombok.RequiredArgsConstructor;
import me.emate.matefront.contents.adaptor.ContentsAdaptor;
import me.emate.matefront.contents.dto.ContentsDetailResponseDto;
import me.emate.matefront.contents.dto.CreateContentsRequestDto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentsServiceImpl implements ContentsService {
    private final ContentsAdaptor contentsAdaptor;

    @CacheEvict(value = "categories", allEntries = true)
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
    public ContentsDetailResponseDto viewLatestContents() {
        return contentsAdaptor.requestLatestContents();
    }
}
