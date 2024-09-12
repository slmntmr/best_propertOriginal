package com.team02.best_properta.service.business;

import com.team02.best_properta.entity.concretes.business.Category;
import com.team02.best_properta.entity.concretes.business.CategoryPropertyKey;
import com.team02.best_properta.entity.concretes.business.CategoryPropertyValue;
import com.team02.best_properta.exception.ResourceNotFoundException;
import com.team02.best_properta.payload.mappers.CategoryPropertyKeyMapper;
import com.team02.best_properta.payload.messages.ErrorMessages;
import com.team02.best_properta.payload.messages.SuccessMessages;
import com.team02.best_properta.payload.request.business.CategoryPropertyKeyRequest;
import com.team02.best_properta.payload.response.business.CategoryPropertyKeyResponse;
import com.team02.best_properta.payload.response.business.ResponseMessage;
import com.team02.best_properta.repository.business.CategoryPropertyKeyRepository;
import com.team02.best_properta.repository.business.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CategoryPropertyKeyService {

    private final CategoryPropertyKeyRepository categoryPropertyKeyRepository;
    private final CategoryPropertyKeyMapper categoryPropertyKeyMapper;



    public ResponseMessage<CategoryPropertyKeyResponse> saveCategoryPropertyKey(CategoryPropertyKeyRequest categoryPropertyKeyRequest) {

        // Request'i CategoryPropertyKey entity'sine dönüştür
        CategoryPropertyKey categoryPropertyKey = categoryPropertyKeyMapper.mapCategoryPropertyKeyRequestToCategoryPropertyKey(categoryPropertyKeyRequest);

        // CategoryPropertyKey'i kaydet
        CategoryPropertyKey savedCategoryPropertyKey = categoryPropertyKeyRepository.save(categoryPropertyKey);

        // Response nesnesini oluştur
        CategoryPropertyKeyResponse response = categoryPropertyKeyMapper.mapCategoryPropertyKeyToCategoryPropertyKeyResponse(savedCategoryPropertyKey);

        return ResponseMessage.<CategoryPropertyKeyResponse>builder()
                .message(SuccessMessages.CATEGORY_PROPERTY_KEY_SAVED)
                .object(response)
                .httpStatus(HttpStatus.CREATED)
                .build();

    }

    public CategoryPropertyKeyResponse updatePropertyKey(Long id, CategoryPropertyKeyRequest propertyKeyRequest) {

        // Property key var mı kontrol et
        CategoryPropertyKey existingPropertyKey = categoryPropertyKeyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property key not found with id: " + id));

        // builtIn özelliği true ise hata fırlat
        if (existingPropertyKey.getBuiltIn()) {
            throw new RuntimeException("Cannot update built-in property keys.");
        }

        // Property key'i güncelle
        existingPropertyKey.setName(propertyKeyRequest.getName());
        existingPropertyKey.setType(propertyKeyRequest.getType());

        // Güncellenmiş property key'i kaydet
        CategoryPropertyKey updatedPropertyKey = categoryPropertyKeyRepository.save(existingPropertyKey);

        // Response nesnesine dönüştür ve döndür
        return categoryPropertyKeyMapper.mapCategoryPropertyKeyToCategoryPropertyKeyResponse(updatedPropertyKey);

    }


    public ResponseMessage deletePropertyKeyById(Long id) {
        isCategoryPropertyKey(id);
        categoryPropertyKeyRepository.deleteById(id);

        return ResponseMessage.builder()
                .message(SuccessMessages.CATEGORY_PROPERTY_KEY_DELETE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    private CategoryPropertyKey isCategoryPropertyKey (Long id){

        return categoryPropertyKeyRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_CATEGORY_PROPERTYKEY_MESSAGE, id)));
    }


    //**********************************************************


    public List<CategoryPropertyKey> getPropertyKeysByCategoryId(Long categoryId) {
        return categoryPropertyKeyRepository.findByCategoryId(categoryId);
    }

}