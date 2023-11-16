package me.emate.matefront.contents.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.emate.matefront.tag.dto.TagListResponseDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContentsDetailResponseDto {

  private Integer contentsNo;
  private String category;
  private List<TagListResponseDto> tags;
  private boolean isDeleted;
  private boolean isHidden;
  private String subject;
  private String detail;
  private Integer views;
  private Integer loving;
  private LocalDateTime createdAt;
  private String thumbnail;
}
