package me.emate.matefront.contents.service;

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

import java.util.List;

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
    public PageableResponse<ContentsListResponseDto> requestContentsByCategory(String category, Pageable pageable) {
        return contentsAdaptor.requestContentsByCategory(category, pageable);
    }

    @Cacheable(value = "mainContents")
    @Override
    public List<ContentsListResponseDto> requestLatestContents() {
        List<ContentsListResponseDto> responses = contentsAdaptor.requestLatestContents();
        for (ContentsListResponseDto response : responses) {
            response.setUrlPath(response.getSubject().replace(" ", "-"));
        }

        return responses;
    }
}
