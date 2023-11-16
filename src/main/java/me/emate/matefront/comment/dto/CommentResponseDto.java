package me.emate.matefront.comment.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CommentResponseDto {

  private Long commentNo;
  private Long momNo;
  private String nickName;
  private String content;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
  private Integer memberNo;
  private boolean deleted;
  private boolean secret;
}
