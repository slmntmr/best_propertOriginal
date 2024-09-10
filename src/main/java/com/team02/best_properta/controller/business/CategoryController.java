package com.team02.best_properta.controller.business;

import com.team02.best_properta.entity.concretes.business.CategoryPropertyKey;
import com.team02.best_properta.payload.request.business.CategoryPropertyKeyRequest;
import com.team02.best_properta.payload.request.business.CategoryRequest;
import com.team02.best_properta.payload.response.business.CategoryPropertyKeyResponse;
import com.team02.best_properta.payload.response.business.CategoryResponse;
import com.team02.best_properta.payload.response.business.ResponseMessage;
import com.team02.best_properta.service.business.CategoryPropertyKeyService;
import com.team02.best_properta.service.business.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService categoryService;
    private final CategoryPropertyKeyService categoryPropertyKeyService;


    // C01 --> ENDPOINT

    @GetMapping("/categories") // http:localhost:8080/categories/?page=1&size=10&sort=id&type=asc
    //  @PreAuthorize("hasAnyAuthority('ANONYMOUS')")
    public Page<CategoryResponse> findByCategoryByPage (
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort,
            @RequestParam(value = "type") String type
    ){
        return categoryService.findByCategoryByPage(page,size,sort,type);
    }

    // C02 --> ENDPOINT

    @GetMapping("/categories/admin")
    //  @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public Page<CategoryResponse> getAllCategoriesForAdmin (
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sort") String sort,
            @RequestParam(value = "type") String type
    ){
        return categoryService.findByCategoryByPage(page,size,sort,type);
    }

    // C03 --> ENDPOINT

    @GetMapping("/{id}")  // http://localhost:8080//categories/1
    //  @PreAuthorize("hasAnyAuthority('ANONYMOUS')")
    public CategoryResponse getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    // C04 --> ENDPOINT

    @PostMapping("/categories") // http://localhost:8080/categories + POST + JSON
    //  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
        CategoryResponse createdCategory = categoryService.saveCategory(categoryRequest);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    // C05 --> ENDPOINT

    @PutMapping("/{id}") // http://localhost:8080/categories/1 + JSON
    //  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseMessage<CategoryResponse> updateCategoryById(@PathVariable Long id,
                                                                @RequestBody @Valid CategoryRequest categoryRequest){

        return categoryService.updateCategoryById(id, categoryRequest);
    }

    // C06 --> ENDPOINT

    @DeleteMapping("/{id}") // http://localhost:8080/categories/1
    //  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseMessage<?> deleteCategoryById(@PathVariable Long id){
        return categoryService.deleteCategoryById(id);
    }

    // C07 --> ENDPOINT

    @GetMapping("/{id}//properties") // http://localhost:8080/categories/{id}//properties
    //  @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<List<CategoryPropertyKey>> getCategoryPropertyKeyById(@PathVariable Long id) {

        List<CategoryPropertyKey> categoryPropertyKeyList = categoryService.getCategoryPropertyKeyById(id);
        return ResponseEntity.ok(categoryPropertyKeyList);

    }


    // C08 --> ENDPOINT

    @PostMapping("/{id}/properties/saveCategoryPropertyKey") // http://localhost:8080/categories/saveCategoryPropertyKey + POST + JSON
    //@PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseMessage<CategoryPropertyKeyResponse> saveCategoryPropertyKey (@RequestBody @Valid CategoryPropertyKeyRequest categoryPropertyKeyRequest) {
        return categoryPropertyKeyService.saveCategoryPropertyKey(categoryPropertyKeyRequest);
    }

    // C11 --> ENDPOINT SLUG
    @GetMapping("/{slug}")  // http://localhost:8080/categories
    //  @PreAuthorize("hasAnyAuthority('ANONYMOUS')")
    public ResponseEntity<CategoryResponse> getCategoryBySlug(@PathVariable String slug) {
        CategoryResponse categoryResponse = categoryService.getCategoryBySlug(slug);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }






    @GetMapping("/count/active")
    public int getActiveCategoriesCount() {
        return categoryService.countActiveCategories();
    }





}