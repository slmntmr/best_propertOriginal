package com.team02.best_properta.payload.mappers;

import com.team02.best_properta.entity.concretes.business.Category;
import com.team02.best_properta.entity.concretes.business.CategoryPropertyKey;
import com.team02.best_properta.payload.request.business.CategoryRequest;
import com.team02.best_properta.payload.response.business.CategoryResponse;
import com.team02.best_properta.repository.business.CategoryRepository;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Data
@Component
public class CategoryMapper {

    public CategoryResponse mapCategoryToCategoryResponse(Category category) {

        return CategoryResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .icon(category.getIcon())
                .builtIn(category.getBuiltIn())
                .seq(category.getSeq())
                .slug(category.getSlug())
                .isActive(category.getIsActive())
                .createAt(category.getCreateAt())
                .updateAt(category.getUpdateAt())
                .adverts(category.getAdverts())
                .categoryPropertyKeys(category.getCategoryPropertyKeys())
                .build();

    }

    public Category mapCategoryRequestToCategory(CategoryRequest categoryRequest,
                                                 List<CategoryPropertyKey> categoryPropertyKeys){

        return Category.builder()
                .title(categoryRequest.getTitle())
                .icon(categoryRequest.getIcon())
                .builtIn(categoryRequest.getBuiltIn())
                .seq(categoryRequest.getSeq())
                .slug(categoryRequest.getSlug())
                .isActive(categoryRequest.getIsActive())
                .createAt(categoryRequest.getCreateAt())
                .categoryPropertyKeys(categoryPropertyKeys)
                .build();

    }


    public Category mapCategoryRequestToUpdatedCategory(Long id, CategoryRequest categoryRequest, List<CategoryPropertyKey> categoryPropertyKeys) {

        return Category.builder()
                .id(id)
                .title(categoryRequest.getTitle())
                .icon(categoryRequest.getIcon())
                .builtIn(categoryRequest.getBuiltIn())
                .seq(categoryRequest.getSeq())
                .slug(categoryRequest.getSlug())
                .isActive(categoryRequest.getIsActive())
                .createAt(categoryRequest.getCreateAt())
                .categoryPropertyKeys(categoryPropertyKeys)
                .build();

    }
}