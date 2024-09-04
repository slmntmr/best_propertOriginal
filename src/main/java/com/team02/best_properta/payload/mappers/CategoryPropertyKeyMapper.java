package com.team02.best_properta.payload.mappers;

import com.team02.best_properta.entity.concretes.business.CategoryPropertyKey;
import com.team02.best_properta.payload.request.business.CategoryPropertyKeyRequest;
import com.team02.best_properta.payload.response.business.CategoryPropertyKeyResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CategoryPropertyKeyMapper {


    public CategoryPropertyKey mapCategoryPropertyKeyRequestToCategoryPropertyKey(CategoryPropertyKeyRequest categoryPropertyKeyRequest) {
        return CategoryPropertyKey.builder()
                .category(categoryPropertyKeyRequest.getCategoryPropertyKey().getCategory())
                .name(categoryPropertyKeyRequest.getName())
                .builtIn(categoryPropertyKeyRequest.isBuiltIn())
                .categoryPropertyValues(categoryPropertyKeyRequest.getCategoryPropertyKey().getCategoryPropertyValues())
                .build();

    }

    public CategoryPropertyKeyResponse mapCategoryPropertyKeyToCategoryPropertyKeyResponse(CategoryPropertyKey categoryPropertyKey){

        return CategoryPropertyKeyResponse.builder()
                .id(categoryPropertyKey.getId())
                .name(categoryPropertyKey.getName())
                .builtIn(categoryPropertyKey.getBuiltIn())
                .category(categoryPropertyKey.getCategory())
                .type(categoryPropertyKey.getName())
                .categoryPropertyValues(categoryPropertyKey.getCategoryPropertyValues())
                .build();
    }

    // !! update için
    public CategoryPropertyKey mapCategoryPropertyKeyRequestToUpdatedCategoryPropertyKey(Long id, CategoryPropertyKeyRequest categoryPropertyKeyRequest) {
        return mapCategoryPropertyKeyRequestToCategoryPropertyKey(categoryPropertyKeyRequest)
                .toBuilder()
                .id(id)
                .build();
    }


}