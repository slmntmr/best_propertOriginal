
/*
package com.team02.best_properta.controller.business;

import com.team02.best_properta.entity.concretes.business.TourRequest;
import com.team02.best_properta.payload.request.business.TourRequestAdmin;
import com.team02.best_properta.payload.request.business.TourRequestDTO;
import com.team02.best_properta.payload.request.business.TourRequestDto;
import com.team02.best_properta.payload.request.business.TourRequestUpdateRequest;
import com.team02.best_properta.payload.response.business.TourRequestCancelResponse;
import com.team02.best_properta.payload.response.business.TourRequestResponse;
import com.team02.best_properta.service.business.TourRequestsService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/report")
public class TourRequestController {

    private final TourRequestsService tourRequestsService;



    @GetMapping("/tour-requests")
    public List<TourRequestResponse> getTourRequests(
            @RequestParam(value = "date1", required = false) LocalDate date1,
            @RequestParam(value = "date2", required = false) LocalDate date2,
            @RequestParam(value = "status", required = false) String status) {

        List<TourRequest> tourRequests = tourRequestsService.getTourRequests(date1, date2, status);

        return tourRequests.stream()
                .map(tour -> new TourRequestResponse(tour.getId(), tour.getTourDate()))
                .toList();
    }

//*************************************************************************************

    @GetMapping("/auth")
    public List<TourRequestDTO> getAuthenticatedUserTourRequests(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "category_id") String sort,
            @RequestParam(defaultValue = "asc") String type) {

        Long ownerId = Long.parseLong(userDetails.getUsername());
        return tourRequestsService.getAuthenticatedUserTourRequests(ownerId, q, page, size, sort, type);
    }

    @GetMapping("/admin")
    public List<TourRequestAdmin> getAllTourRequests(
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "category_id") String sort,
            @RequestParam(defaultValue = "asc") String type) {

        return tourRequestsService.getTourRequests(q, page, size, sort, type);
    }





    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<TourRequest> createTourRequest(@RequestBody TourRequestResponse requestDto) {
        com.team02.best_properta.entity.concretes.business.TourRequest createdTourRequest = tourRequestsService.createTourRequest(requestDto);
        return ResponseEntity.ok(createdTourRequest);
    }


    @GetMapping("/{id}/auth")
    public TourRequestAdmin getTourRequestDetail(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {

        Long ownerId = Long.parseLong(userDetails.getUsername());
        return tourRequestsService.getTourRequestDetail(id, ownerId);
    }


    @PutMapping("/{id}/auth")
    public ResponseEntity<TourRequestResponse> updateTourRequest(@PathVariable Long id,
                                                                 @RequestBody TourRequestUpdateRequest requestDto) {
        TourRequestResponse response = tourRequestsService.updateTourRequest(id, requestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }




    @PatchMapping("/{id}/cancel")
    public ResponseEntity<TourRequestCancelResponse> cancelTourRequest(@PathVariable Long id) {
        TourRequestCancelResponse response = tourRequestsService.cancelTourRequest(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @PatchMapping("/{id}/approve")
    public ResponseEntity<TourRequestCancelResponse> approveTourRequest(@PathVariable Long id) {
        TourRequestCancelResponse response = tourRequestsService.approveTourRequest(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
*/
