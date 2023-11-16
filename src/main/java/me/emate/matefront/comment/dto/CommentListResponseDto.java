package me.emate.matefront.comment.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentListResponseDto {

  private Long commentNo;
  private String nickName;
  private String content;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;
  private Integer memberNo;
  private boolean deleted;
  private boolean secret;
  private List<CommentResponseDto> childComments;
}
