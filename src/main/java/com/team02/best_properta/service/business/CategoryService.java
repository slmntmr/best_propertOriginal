package com.team02.best_properta.service.business;

import com.team02.best_properta.entity.concretes.business.Category;
import com.team02.best_properta.entity.concretes.business.CategoryPropertyKey;
import com.team02.best_properta.exception.ResourceNotFoundException;
import com.team02.best_properta.payload.mappers.CategoryMapper;
import com.team02.best_properta.payload.mappers.CategoryPropertyKeyMapper;
import com.team02.best_properta.payload.messages.ErrorMessages;
import com.team02.best_properta.payload.messages.SuccessMessages;
import com.team02.best_properta.payload.request.business.CategoryPropertyKeyRequest;
import com.team02.best_properta.payload.request.business.CategoryRequest;
import com.team02.best_properta.payload.response.business.CategoryPropertyKeyResponse;
import com.team02.best_properta.payload.response.business.CategoryResponse;
import com.team02.best_properta.payload.response.business.ResponseMessage;
import com.team02.best_properta.repository.business.CategoryPropertyKeyRepository;
import com.team02.best_properta.repository.business.CategoryRepository;
import com.team02.best_properta.service.helper.PageableHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final PageableHelper pageableHelper;
    private final CategoryMapper categoryMapper;
    private final CategoryPropertyKeyMapper categoryPropertyKeyMapper;
    private final CategoryPropertyKeyService categoryPropertyKeyService;
    private final CategoryPropertyKeyRepository categoryPropertyKeyRepository;



    public Page<CategoryResponse> findByCategoryByPage(int page, int size, String sort, String type) {

        Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, type);
        return categoryRepository.findAll(pageable).map(categoryMapper::mapCategoryToCategoryResponse);

    }


    public CategoryResponse getCategoryById(Long id) {
        return categoryMapper.mapCategoryToCategoryResponse(isCategoryExistById(id));
    }

    public Category isCategoryExistById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_CATEGORY_MESSAGE, id)));
    }

    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {

        List<CategoryPropertyKey> categoryPropertyKeyList = new ArrayList<>();

        Category category = categoryMapper.mapCategoryRequestToCategory(categoryRequest, categoryPropertyKeyList);

        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.mapCategoryToCategoryResponse(savedCategory);


    }


    public ResponseMessage<CategoryResponse> updateCategoryById(Long id, CategoryRequest categoryRequest) {

        // Mevcut kategoriyi id ile bul veya hata fırlat
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        // builtIn özelliği true ise hata fırlat
        if (existingCategory.getBuiltIn()) {
            throw new RuntimeException("Cannot update built-in categories.");
        }

        // Güncellenmiş kategoriyi oluştur
        Category categoryToSave = categoryMapper.mapCategoryRequestToUpdatedCategory(id, categoryRequest, existingCategory.getCategoryPropertyKeys());

        // Kategoriyi güncelle
        Category categoryUpdated = categoryRepository.save(categoryToSave);

        // Yanıt mesajını oluştur ve geri döndür
        return ResponseMessage.<CategoryResponse>builder()
                .message(SuccessMessages.CATEGORY_UPDATE)
                .httpStatus(HttpStatus.CREATED)
                .object(categoryMapper.mapCategoryToCategoryResponse(categoryUpdated))
                .build();
    }



    public ResponseMessage<?> deleteCategoryById(Long id) {

        isCategoryExistById(id);

        categoryRepository.deleteById(id);

        return ResponseMessage.builder()
                .message(SuccessMessages.CATEGORY_DELETE)
                .httpStatus(HttpStatus.OK)
                .build();

    }

    public List<CategoryPropertyKey> getCategoryPropertyKeyById(Long id) {
        CategoryPropertyKey categoryPropertyKey = isCategoryPropertyKeyExist(id);
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(Category::getCategoryPropertyKeys).orElse(Collections.emptyList());
    }

    private CategoryPropertyKey isCategoryPropertyKeyExist(Long id) {
        return categoryPropertyKeyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_CATEGORY_PROPERTYKEY_MESSAGE, id)));
    }




    private CategoryPropertyKeyResponse mapPropertyKeyToResponse(CategoryPropertyKey propertyKey) {
        return CategoryPropertyKeyResponse.builder()
                .id(propertyKey.getId())
                .name(propertyKey.getName())
                .type(propertyKey.getCategory().getTitle()) // ya da başka bir alan
                .build();
    }


    public CategoryResponse getCategoryBySlug(String slug) {
        Category category = categoryRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with slug: " + slug));
        return categoryMapper.mapCategoryToCategoryResponse(category);
    }



    public int countActiveCategories() {
        return categoryRepository.countByIsActiveTrue();
    }








}