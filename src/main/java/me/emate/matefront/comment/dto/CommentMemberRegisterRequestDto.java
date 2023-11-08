package me.emate.matefront.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentMemberRegisterRequestDto {
    private Integer contentsNo;
    private Integer memberNo;
    private Long momNo;
    private String content;
    private boolean secret;
}
