package me.emate.matefront.contents.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContentsListResponseDto {

  private String thumbnail;
  private String subject;
  private String urlPath;
  private LocalDateTime createdAt;
  private int loving;

  public void setUrlPath(String urlPath) {
    this.urlPath = urlPath;
  }
}
