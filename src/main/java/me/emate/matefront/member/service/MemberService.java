package me.emate.matefront.member.service;

import jakarta.servlet.http.HttpServletResponse;
import me.emate.matefront.member.dto.SignupRequestDto;

public interface MemberService {
    boolean idConflictCheck(String id);

    boolean isNickConflict(String nickname);

    boolean isEmailConflict(String email);

    void signup(SignupRequestDto requestDto);

    void logout(HttpServletResponse response);
}
