package me.emate.matefront.tag.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
public class TagListResponseDto {
    private String tagNo;
    private String tagName;
    private String tagColor;
    @Setter
    private String tagUrl;
}
