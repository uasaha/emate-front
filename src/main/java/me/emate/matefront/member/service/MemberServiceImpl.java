package me.emate.matefront.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.emate.matefront.member.adaptor.MemberAdaptor;
import me.emate.matefront.member.dto.CheckIDRequestDto;
import me.emate.matefront.member.dto.CheckNicknameRequestDto;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberAdaptor memberAdaptor;

    @Override
    public boolean idConflictCheck(String id) {
        CheckIDRequestDto requestDto = new CheckIDRequestDto(id);
        return Boolean.TRUE.equals(memberAdaptor.idConflictCheck(requestDto).getBody());
    }

    @Override
    public boolean isNickConflict(String nickname) {
        CheckNicknameRequestDto requestDto = new CheckNicknameRequestDto(nickname);
        return Boolean.TRUE.equals(memberAdaptor.nickConflictCheck(requestDto).getBody());
    }
}
