package me.emate.matefront.member.adaptor;

import me.emate.matefront.member.dto.*;
import org.springframework.http.ResponseEntity;

public interface MemberAdaptor {
    MemberDetailResponseDto requestAuthMemberInfo(String accessToken);

    ResponseEntity<Void> loginRequest(MemberLoginRequestDto loginRequest);

    ResponseEntity<Boolean> idConflictCheck(CheckIDRequestDto requestDto);

    ResponseEntity<Boolean> nickConflictCheck(CheckNicknameRequestDto requestDto);

    ResponseEntity<Boolean> emailConflictCheck(CheckEmailRequestDto requestDto);

    ResponseEntity<Void> signup(SignupRequestDto requestDto);

    void logout();

    ResponseEntity<Void> tokenReIssueRequest(String accessToken);
}
