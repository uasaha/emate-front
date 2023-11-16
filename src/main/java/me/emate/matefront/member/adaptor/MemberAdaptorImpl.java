package me.emate.matefront.member.adaptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.matefront.config.ToBackConfig;
import me.emate.matefront.member.dto.*;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

import static me.emate.matefront.utils.JwtUtils.AUTH_HEADER;
import static me.emate.matefront.utils.JwtUtils.TOKEN_TYPE;
import static me.emate.matefront.utils.Utils.makeHeader;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberAdaptorImpl implements MemberAdaptor {
    private final RestTemplate restTemplate;
    private final ToBackConfig toBackConfig;
    private static final String MEMBER_URL = "/members";
    private static final String AUTH_URL = "/auth";

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
    public ResponseEntity<Boolean> idConflictCheck(CheckIdRequestDto requestDto) {
        HttpEntity<CheckIdRequestDto> entity =
                new HttpEntity<>(requestDto, makeHeader());

        return restTemplate.exchange(
                toBackConfig.getBackUrl() + MEMBER_URL + "/signup/id",
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
                toBackConfig.getBackUrl() + MEMBER_URL + "/signup/nickname",
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
                toBackConfig.getBackUrl() + MEMBER_URL + "/signup/email",
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
    public void logout(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        if (Objects.nonNull(accessToken)) {
            headers.add("Authorization", accessToken);
        }

        restTemplate.exchange(
                toBackConfig.getBackUrl() + AUTH_URL + "/logout",
                HttpMethod.GET,
                new HttpEntity<>(headers),
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
