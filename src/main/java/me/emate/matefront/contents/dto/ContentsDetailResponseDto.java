package me.emate.matefront.contents.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContentsDetailResponseDto {
    private Integer contentsNo;
    private String category;
    private String tags;
    private boolean isDeleted;
    private boolean isHidden;
    private String subject;
    private String detail;
    private Integer views;
    private Integer loving;
    private LocalDateTime createdAt;
}
