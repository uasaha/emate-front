package me.emate.matefront.member.adaptor;

import me.emate.matefront.member.dto.CheckEmailRequestDto;
import me.emate.matefront.member.dto.CheckIdRequestDto;
import me.emate.matefront.member.dto.CheckNicknameRequestDto;
import me.emate.matefront.member.dto.MemberDetailResponseDto;
import me.emate.matefront.member.dto.MemberLoginRequestDto;
import me.emate.matefront.member.dto.SignupRequestDto;
import org.springframework.http.ResponseEntity;

public interface MemberAdaptor {

  MemberDetailResponseDto requestAuthMemberInfo(String accessToken);

  ResponseEntity<Void> loginRequest(MemberLoginRequestDto loginRequest);

  ResponseEntity<Boolean> idConflictCheck(CheckIdRequestDto requestDto);

  ResponseEntity<Boolean> nickConflictCheck(CheckNicknameRequestDto requestDto);

  ResponseEntity<Boolean> emailConflictCheck(CheckEmailRequestDto requestDto);

  ResponseEntity<Void> signup(SignupRequestDto requestDto);

  void logout(String accessToken);

  ResponseEntity<Void> tokenReIssueRequest(String accessToken);
}
