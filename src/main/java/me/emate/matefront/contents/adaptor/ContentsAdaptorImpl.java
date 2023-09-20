package me.emate.matefront.contents.adaptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.matefront.config.ToBackConfig;
import me.emate.matefront.contents.dto.ContentsDetailResponseDto;
import me.emate.matefront.contents.dto.CreateContentsRequestDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import static me.emate.matefront.utils.Utils.makeHeader;

@Slf4j
@Component
@RequiredArgsConstructor
public class ContentsAdaptorImpl implements ContentsAdaptor {
    private final RestTemplate restTemplate;
    private final ToBackConfig toBackConfig;
    private static final String CONTENTS_URL = "/contents";

    @Override
    public ContentsDetailResponseDto requestContentsByNo(Integer contentsNo) {
        HttpHeaders headers = makeHeader();

        return restTemplate.exchange(
                toBackConfig.getBackUrl() + CONTENTS_URL + "/" + contentsNo,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                ContentsDetailResponseDto.class).getBody();
    }

    @Override
    public ContentsDetailResponseDto registerContents(CreateContentsRequestDto requestDto) {
        return restTemplate.exchange(
                toBackConfig.getBackUrl() + CONTENTS_URL + "/register",
                HttpMethod.POST,
                new HttpEntity<>(requestDto, makeHeader()),
                ContentsDetailResponseDto.class).getBody();
    }
}
