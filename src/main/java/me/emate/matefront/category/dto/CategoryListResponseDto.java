package me.emate.matefront.category.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CategoryListResponseDto {
    private Integer categoryNo;
    private String categoryName;
    private Long contentsCnt;
}
