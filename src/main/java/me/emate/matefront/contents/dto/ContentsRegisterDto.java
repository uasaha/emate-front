package me.emate.matefront.contents.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ContentsRegisterDto {

  private Integer categoryNo;
  private String tagNo;
  private String hidden;
  private String thumbnail;
  private String subject;
  private String detail;
}
