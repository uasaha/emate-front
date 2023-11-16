package me.emate.matefront.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberLoginRequestDto {

  private String id;
  private String pwd;
}
