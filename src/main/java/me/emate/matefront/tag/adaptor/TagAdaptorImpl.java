package me.emate.matefront.tag.adaptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.matefront.category.dto.CategoryListResponseDto;
import me.emate.matefront.config.ToBackConfig;
import me.emate.matefront.tag.dto.TagListResponseDto;
import me.emate.matefront.utils.Utils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TagAdaptorImpl implements TagAdaptor {
    private final RestTemplate restTemplate;
    private final ToBackConfig toBackConfig;
    private static final String TAG_URL = "/tag";

    @Override
    public List<TagListResponseDto> getAllTags() {
        String url = UriComponentsBuilder
                .fromHttpUrl(toBackConfig.getBackUrl() + TAG_URL)
                .encode()
                .toUriString();

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(Utils.makeHeader()),
                new ParameterizedTypeReference<List<TagListResponseDto>>() {}
        ).getBody();
    }
}
