package me.emate.matefront.member.adaptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.matefront.config.ToBackConfig;
import me.emate.matefront.member.dto.CheckEmailRequestDto;
import me.emate.matefront.member.dto.CheckIDRequestDto;
import me.emate.matefront.member.dto.CheckNicknameRequestDto;
import me.emate.matefront.utils.Utils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static me.emate.matefront.utils.Utils.makeHeader;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberAdaptorImpl implements MemberAdaptor {
    private final RestTemplate restTemplate;
    private final ToBackConfig toBackConfig;
    private static final String MEMBER_URL = "/member";

    @Override
    public ResponseEntity<Boolean> idConflictCheck(CheckIDRequestDto requestDto) {
        HttpEntity<CheckIDRequestDto> entity =
                new HttpEntity<>(requestDto, makeHeader());

        return restTemplate.exchange(
                toBackConfig.getBackUrl() + MEMBER_URL + "/signup/idcheck",
                HttpMethod.POST,
                entity,
                Boolean.class
        );
    }

    @Override
    public ResponseEntity<Boolean> nickConflictCheck(CheckNicknameRequestDto requestDto) {
        HttpEntity<CheckNicknameRequestDto> entity =
                new HttpEntity<>(requestDto, makeHeader());

        return restTemplate.exchange(
                toBackConfig.getBackUrl() + MEMBER_URL + "/signup/nickcheck",
                HttpMethod.POST,
                entity,
                Boolean.class
        );
    }

    @Override
    public ResponseEntity<Boolean> emailConflictCheck(CheckEmailRequestDto requestDto) {
        HttpEntity<CheckEmailRequestDto> entity =
                new HttpEntity<>(requestDto, makeHeader());

        return restTemplate.exchange(
                toBackConfig.getBackUrl() + MEMBER_URL + "/signup/emailcheck",
                HttpMethod.POST,
                entity,
                Boolean.class
        );
    }
}
