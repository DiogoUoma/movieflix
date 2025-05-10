package br.com.movieflix.movieflix.mapper;

import br.com.movieflix.movieflix.controller.request.CategoryRequest;
import br.com.movieflix.movieflix.controller.response.CategoryResponse;
import br.com.movieflix.movieflix.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category toCategory(CategoryRequest categoryRequest) {
        return Category
                .builder()
                .name(categoryRequest.name())
                .build();
    }

    public static CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
