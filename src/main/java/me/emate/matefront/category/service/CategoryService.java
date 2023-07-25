package me.emate.matefront.category.service;

import me.emate.matefront.category.dto.CategoryListResponseDto;

import java.util.List;

public interface CategoryService {
    List<CategoryListResponseDto> getAllCategories();
}
