package me.emate.matefront.comment.adaptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.matefront.comment.dto.CommentListResponseDto;
import me.emate.matefront.comment.dto.CommentMemberRegisterRequestDto;
import me.emate.matefront.comment.dto.CommentNoMemberRegisterRequestDto;
import me.emate.matefront.comment.dto.CommentResponseDto;
import me.emate.matefront.config.ToBackConfig;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static me.emate.matefront.utils.Utils.makeHeader;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommentAdaptorImpl implements CommentAdaptor {
    private final RestTemplate restTemplate;
    private final ToBackConfig toBackConfig;
    private static final String COMMENT_URL = "/comments";

    @Override
    public List<CommentListResponseDto> getCommentsByContentsNo(Integer contentsNo) {
        HttpHeaders headers = makeHeader();

        return restTemplate.exchange(
                toBackConfig.getBackUrl() + COMMENT_URL + "/contents/" + contentsNo,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<List<CommentListResponseDto>>() {}
        ).getBody();
    }

    @Override
    public void registerAnonymousComment(CommentNoMemberRegisterRequestDto requestDto) {
        restTemplate.exchange(
                toBackConfig.getBackUrl() + COMMENT_URL + "/anonymous",
                HttpMethod.POST,
                new HttpEntity<>(requestDto, makeHeader()),
                new ParameterizedTypeReference<CommentResponseDto>() {})
                .getBody();
    }

    @Override
    public void registerMemberComment(CommentMemberRegisterRequestDto requestDto) {
        restTemplate.exchange(
                toBackConfig.getBackUrl() + COMMENT_URL + "/member",
                HttpMethod.POST,
                new HttpEntity<>(requestDto, makeHeader()),
                new ParameterizedTypeReference<Void>() {})
                .getBody();
    }
}
