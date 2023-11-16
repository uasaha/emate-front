package me.emate.matefront.category.adaptor;

import java.util.List;
import me.emate.matefront.category.dto.CategoryListResponseDto;

public interface CategoryAdaptor {

  List<CategoryListResponseDto> getAllCategories();
}
