package com.team02.best_properta.controller.business;

import com.team02.best_properta.payload.request.business.CategoryPropertyKeyRequest;
import com.team02.best_properta.payload.response.business.CategoryPropertyKeyResponse;
import com.team02.best_properta.payload.response.business.ResponseMessage;
import com.team02.best_properta.service.business.CategoryPropertyKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories/properties")
public class CategoryPropertyKeyController {

    private final CategoryPropertyKeyService categoryPropertyKeyService;

    // C09 ENDPOİNT

    @PutMapping("/{id}") // http://localhost:8080/categories/properties + JSON
    //  @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<CategoryPropertyKeyResponse> updatePropertyKey(
            @PathVariable Long id,
            @RequestBody CategoryPropertyKeyRequest propertyKeyRequest) {

        CategoryPropertyKeyResponse updatedPropertyKey = categoryPropertyKeyService.updatePropertyKey(id, propertyKeyRequest);
        return new ResponseEntity<>(updatedPropertyKey, HttpStatus.OK);
    }

    // C10 ENDPOİNT


    @DeleteMapping("/{id}") // http://localhost:8080/categories/properties
    //  @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseMessage<?> deletePropertyKeyById(@PathVariable Long id) {
        return categoryPropertyKeyService.deletePropertyKeyById(id);

    }
}