package com.team02.best_properta.controller.business;

import com.team02.best_properta.entity.concretes.business.AdvertType;
import com.team02.best_properta.payload.request.business.AdvertTypePayload;
import com.team02.best_properta.payload.response.business.AdvertTypeResponse;
import com.team02.best_properta.repository.business.AdvertTypeRepository;
import com.team02.best_properta.service.business.AdvertTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdvertTypeController {

    private AdvertTypeService advertTypeService;




    // T01
    @GetMapping      // http://localhost:8080/advert-types  + GET
    //@PreAuthorize("hasAnyAuthority('Anonymous')")
    public ResponseEntity<List<AdvertTypeResponse>> getAllAdvertTypes() {
        List<AdvertTypeResponse> advertTypes = advertTypeService.getAllAdvertTypes();
        return ResponseEntity.ok(advertTypes);
    }

    // T02
    @GetMapping("/{id}")     // http://localhost:8080/advert-types/23 + GET
    //@PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<AdvertType> getAdvertTypeById(@PathVariable Long id) {
        AdvertType advertType = advertTypeService.getAdvertTypeById(id);
        if (advertType != null) {
            return ResponseEntity.ok(advertType);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // T03
    @PostMapping        // http://localhost:8080/advert-types + JSON + POST
    //@PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<AdvertType> createAdvertType(@RequestBody AdvertTypePayload advertTypePayload) {
        AdvertType createdAdvertType = advertTypeService.createAdvertType(advertTypePayload);
        return ResponseEntity.ok(createdAdvertType);
    }

    // T04
    @PutMapping("/{id}")    // http://localhost:8080/advert-types/23 + JSON
    //@PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<AdvertType> updateAdvertType(@PathVariable Long id, @RequestBody AdvertType advertTypeDetails) {
        AdvertType updatedAdvertType = advertTypeService.updateAdvertType(id, advertTypeDetails);
        if (updatedAdvertType != null) {
            return ResponseEntity.ok(updatedAdvertType);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // T05
    @DeleteMapping("/{id}")     // http://localhost:8080/advert-types/23 + JSON
    //@PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<?> deleteAdvertType(@PathVariable Long id) {
        try {
            AdvertType deletedAdvertType = advertTypeService.deleteAdvertType(id);
            return ResponseEntity.ok(deletedAdvertType);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}