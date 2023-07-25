package me.emate.matefront.category.adaptor;

import me.emate.matefront.category.dto.CategoryListResponseDto;

import java.util.List;

public interface CategoryAdaptor {
    List<CategoryListResponseDto> getAllCategories();
}
