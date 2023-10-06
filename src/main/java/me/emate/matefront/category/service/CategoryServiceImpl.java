package me.emate.matefront.category.service;

import lombok.RequiredArgsConstructor;
import me.emate.matefront.category.adaptor.CategoryAdaptor;
import me.emate.matefront.category.dto.CategoryListResponseDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryAdaptor categoryAdaptor;

    @Cacheable(value = "categories")
    @Override
    public List<CategoryListResponseDto> getAllCategories() {
        return categoryAdaptor.getAllCategories();
    }
}
