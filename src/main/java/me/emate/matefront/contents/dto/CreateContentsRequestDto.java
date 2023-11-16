package me.emate.matefront.contents.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateContentsRequestDto {

  private Integer categoryNo;
  private List<Integer> tagNo;
  private Boolean hidden;
  private String thumbnail;
  private String subject;
  private String detail;
}
