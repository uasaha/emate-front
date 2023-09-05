package me.emate.matefront.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDetailResponseDto {
    private Integer memberNo;
    private String nickname;
    private String email;
    private String intro;
    private List<String> authorities;

    @Override
    public String toString() {
        return memberNo + ", " + nickname;
    }
}
