package me.emate.matefront.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentNoMemberRegisterRequestDto {

  private Integer contentsNo;
  private Long momNo;
  private String nickName;
  private String password;
  private String content;
}
