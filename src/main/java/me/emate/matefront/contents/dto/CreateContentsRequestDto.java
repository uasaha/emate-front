package me.emate.matefront.contents.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateContentsRequestDto {
    private Integer categoryNo;
    private Integer tagNo;
    private String thumbnail;
    private String subject;
    private String detail;
}
