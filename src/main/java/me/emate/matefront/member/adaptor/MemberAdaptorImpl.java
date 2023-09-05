package me.emate.matefront.member.adaptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.matefront.config.ToBackConfig;
import me.emate.matefront.member.dto.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static me.emate.matefront.utils.JwtUtils.AUTH_HEADER;
import static me.emate.matefront.utils.JwtUtils.TOKEN_TYPE;
import static me.emate.matefront.utils.Utils.makeHeader;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberAdaptorImpl implements MemberAdaptor {
    private final RestTemplate restTemplate;
    private final ToBackConfig toBackConfig;
    private static final String MEMBER_URL = "/member";

    @Override
    public MemberDetailResponseDto requestAuthMemberInfo(String accessToken) {
        HttpHeaders headers = makeHeader();
        headers.add(AUTH_HEADER, TOKEN_TYPE + accessToken);

        return restTemplate.exchange(
                toBackConfig.getBackUrl() + MEMBER_URL + "/details",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                MemberDetailResponseDto.class).getBody();
    }

    @Override
    public ResponseEntity<Void> loginRequest(MemberLoginRequestDto loginRequest) {
        HttpEntity<MemberLoginRequestDto> entity = new HttpEntity<>(loginRequest, makeHeader());

        return restTemplate.exchange(
                toBackConfig.getBackUrl() + "/auth/login",
                HttpMethod.POST,
                entity,
                Void.class
        );
    }

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

    @Override
    public ResponseEntity<Void> signup(SignupRequestDto requestDto) {
        HttpEntity<SignupRequestDto> entity =
                new HttpEntity<>(requestDto, makeHeader());

        return restTemplate.exchange(
                toBackConfig.getBackUrl() + MEMBER_URL + "/signup",
                HttpMethod.POST,
                entity,
                Void.class);
    }

    @Override
    public void logout() {
        restTemplate.exchange(
                toBackConfig.getBackUrl() + "/logout",
                HttpMethod.GET,
                new HttpEntity<>(makeHeader()),
                Void.class
        );
    }

    @Override
    public ResponseEntity<Void> tokenReIssueRequest(String accessToken) {
        return restTemplate.exchange(
                toBackConfig.getBackUrl() + "/auth/reissue",
                HttpMethod.POST,
                new HttpEntity<>(accessToken, makeHeader()),
                Void.class
        );
    }
}
