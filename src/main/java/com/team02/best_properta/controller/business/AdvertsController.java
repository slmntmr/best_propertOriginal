package com.team02.best_properta.controller.business;


import com.team02.best_properta.payload.response.business.AdvertResponse;

import com.team02.best_properta.service.business.AdvertsService;
import lombok.AllArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/adverts")
public class AdvertsController {

    private final AdvertsService advertsService;


    @GetMapping
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
    }


}