package com.team02.best_properta.controller.business;

import com.team02.best_properta.entity.concretes.business.Advert;
import com.team02.best_properta.payload.request.business.*;
import com.team02.best_properta.payload.response.business.AdvertResponse;
import com.team02.best_properta.service.business.AdvertsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/adverts")
public class AdvertsController {

    private final AdvertsService advertsService;

 /*   @GetMapping
    public ResponseEntity<List<AdvertResponse>> getAdverts(
            @RequestParam(value = "date1", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date1,
            @RequestParam(value = "date2", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date2,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "status", required = false) String status) {

        LocalDateTime startDate = (date1 != null) ? date1.atStartOfDay() : null;
        LocalDateTime endDate = (date2 != null) ? date2.atTime(LocalTime.MAX) : null;

        List<AdvertResponse> adverts = advertsService.getAdverts(startDate, endDate, category, type, status);
        return ResponseEntity.ok(adverts);
    }*/

    @GetMapping("/search")
    public Page<AdvertResponse> getAdverts(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Long category_id,
            @RequestParam(required = false) Long advert_type_id,
            @RequestParam(required = false) BigDecimal price_start,
            @RequestParam(required = false) BigDecimal price_end,
            @RequestParam(required = false) Integer status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "category_id") String sort,
            @RequestParam(defaultValue = "asc") String type) {

        return advertsService.getAdverts(q, category_id, advert_type_id, price_start, price_end, status, page, size, sort, type);
    }

    @GetMapping("/cities")
    public List<CityDTO> getAdvertsByCity() {
        return advertsService.getAdvertCountsByCity();
    }

    @GetMapping("/categories")
    public List<CategoryDTO> getAdvertsGroupedByCategory() {
        return advertsService.getAdvertsGroupedByCategory();
    }

    @GetMapping("/popular/{amount}")
    public ResponseEntity<List<Advert>> getPopularAdverts(@PathVariable int amount) {
        List<Advert> popularAdverts = advertsService.getPopularAdverts(amount);
        return ResponseEntity.ok(popularAdverts);
    }

    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    @GetMapping("/auth")
    public ResponseEntity<Page<Advert>> getAllCustomerAdverts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "category_id") String sort,
            @RequestParam(defaultValue = "asc") String type) {

        Page<Advert> adverts = advertsService.getAllCustomerAdverts(page, size, sort, type);
        return ResponseEntity.ok(adverts);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @GetMapping("/admin")
    public ResponseEntity<Page<Advert>> getAdvertsAdmin(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Long category_id,
            @RequestParam(required = false) Long advert_type_id,
            @RequestParam(required = false) Double price_start,
            @RequestParam(required = false) Double price_end,
            @RequestParam(required = false) Integer status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "category_id") String sort,
            @RequestParam(defaultValue = "asc") String type) {

        Page<Advert> adverts = advertsService.getAdvertsAdmin(q, category_id, advert_type_id, price_start, price_end, status, page, size, sort, type);
        return new ResponseEntity<>(adverts, HttpStatus.OK);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<AdvertDTO> getAdvertBySlug(@PathVariable String slug) {
        AdvertDTO advertDTO = advertsService.getAdvertBySlug(slug);
        return ResponseEntity.ok(advertDTO);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/{id}/auth")
    public AdvertDTO getAdvertByIdAndUser(@PathVariable Long id) {
        return advertsService.getAdvertByIdDTO(id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping("/{id}/admin")
    public AdvertDTO getAdvertByIdForAdmin(@PathVariable Long id) {
        return advertsService.getAdvertByIdForAdmin(id);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping
    public ResponseEntity<AdvertResponse> createAdvert(@RequestBody AdvertRequest advertRequest) {
        AdvertResponse createdAdvert = advertsService.createAdvert(advertRequest);
        return new ResponseEntity<>(createdAdvert, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/auth/{id}")
    public ResponseEntity<AdvertResponse> updateAdvert(
            @PathVariable Long id,
            @RequestBody AdvertUpdateRequest advertUpdateRequest) {

        AdvertResponse updatedAdvert = advertsService.updateAdvert(id, advertUpdateRequest);
        return ResponseEntity.ok(updatedAdvert);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @PutMapping("/admin/{id}")
    public ResponseEntity<AdvertResponse> updateAdvertByAdminOrManager(
            @PathVariable Long id,
            @RequestBody AdvertUpdateRequest advertUpdateRequest) {

        AdvertResponse updatedAdvert = advertsService.updateAdvertByAdminOrManager(id, advertUpdateRequest);
        return ResponseEntity.ok(updatedAdvert);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Advert> deleteAdvert(@PathVariable Long id) {
        Advert deletedAdvert = advertsService.deleteAdvertById(id);
        return ResponseEntity.ok(deletedAdvert);
    }
}
