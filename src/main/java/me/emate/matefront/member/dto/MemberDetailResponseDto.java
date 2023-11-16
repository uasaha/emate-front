package me.emate.matefront.member.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
