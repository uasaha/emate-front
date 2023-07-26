package me.emate.matefront.member.adaptor;

import me.emate.matefront.member.dto.CheckIDRequestDto;
import me.emate.matefront.member.dto.CheckNicknameRequestDto;
import org.springframework.http.ResponseEntity;

public interface MemberAdaptor {
    ResponseEntity<Boolean> idConflictCheck(CheckIDRequestDto requestDto);

    ResponseEntity<Boolean> nickConflictCheck(CheckNicknameRequestDto requestDto);
}
