package com.team02.best_properta.service.business;


import com.team02.best_properta.entity.concretes.business.TourRequest;
import com.team02.best_properta.payload.mappers.TourRequestMapper;
import com.team02.best_properta.repository.business.AdvertsRepository;
import com.team02.best_properta.repository.business.TourRequestsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TourRequestsService {

    private final TourRequestsRepository tourRequestsRepository;
    private final TourRequestMapper tourRequestMapper;
    private final AdvertsRepository advertsRepository;

    public boolean userHasTourRequests(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null.");
        }
        return tourRequestsRepository.existsByOwnerUserIdOrGuestUserId(userId, userId);
    }

    public long countTourRequests() {
        return tourRequestsRepository.countTourRequests();
    }

    public List<TourRequest> getTourRequests(LocalDate date1, LocalDate date2, String status) {
        return tourRequestsRepository.findTourRequests(date1, date2, status);
    }
/*
    //**************************************************************************




    public boolean userHasTourRequests(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null.");
        }
        return tourRequestsRepository.existsByOwnerUserIdOrGuestUserId(userId, userId);
    }







    public List<TourRequestDto> getAuthenticatedUserTourRequests(Long ownerId, String searchQuery, int page, int size, String sort, String type) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(type), sort));
        List<TourRequest> tourRequests;

        if (searchQuery != null && !searchQuery.isEmpty()) {
            tourRequests = tourRequestsRepository.findByOwnerUser_Id(ownerId); // Search logic can be enhanced
        } else {
            tourRequests = tourRequestsRepository.findByOwnerUser_Id(ownerId);
        }

        return tourRequests.stream()
                .map(tourRequestMapper::mapTourRequestToTourRequestDto)
                .collect(Collectors.toList());
    }



    public List<TourRequestAdmin> getTourRequests(String searchQuery, int page, int size, String sort, String type) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(type), sort));
        List<TourRequest> tourRequests;

        if (searchQuery != null && !searchQuery.isEmpty()) {

            tourRequests = tourRequestsRepository.findAll(pageable).getContent();
        } else {
            tourRequests = tourRequestsRepository.findAll(pageable).getContent();
        }

        return tourRequests.stream()
                .map(tourRequestMapper::mapTourRequestToTourRequestAdminDto)
                .collect(Collectors.toList());
    }


    public TourRequestAdmin getTourRequestDetail(Long id, Long ownerId) {
        TourRequest tourRequest = tourRequestsRepository.findByIdAndOwnerUserId(id, ownerId);
        if (tourRequest == null) {
            throw new RuntimeException("TourRequest not found or you do not have access");
        }
        return tourRequestMapper.mapTourRequestToTourRequestAdminDto(tourRequest);
    }



    public TourRequestAdmin getTourRequestDetail(Long id) {
        TourRequest tourRequest = tourRequestsRepository.findById(id).orElseThrow(() -> new RuntimeException("TourRequest not found"));
        return tourRequestMapper.mapTourRequestToTourRequestAdminDto(tourRequest);
    }



    public TourRequest createTourRequest(TourRequestResponse requestDto) {
        com.team02.best_properta.entity.concretes.business.TourRequest tourRequest = new com.team02.best_properta.entity.concretes.business.TourRequest();
        tourRequest.setTourDate(requestDto.getTourDate());
        tourRequest.setTourTime(requestDto.getTourTime());
        tourRequest.setStatus(0);

        return tourRequestsRepository.save(tourRequest);
    }



    public TourRequestAdmin updateTourRequest(Long id, TourRequestUpdateRequest requestDto) {
        TourRequest tourRequest = tourRequestsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour Request not found"));

        if (tourRequest.getStatus() != 0 && tourRequest.getStatus() != 1) { // Assuming 0 is 'pending' and 1 is 'rejected'
            throw new RuntimeException("Only pending or rejected requests can be updated");
        }

        tourRequest.setTourDate(requestDto.getTourDate());
        tourRequest.setTourTime(requestDto.getTourTime());

        Advert advert = advertsRepository.findById(requestDto.getAdvertId())
                .orElseThrow(() -> new RuntimeException("Advert not found"));
        tourRequest.setAdvert(advert);

        tourRequest.setStatus(0); // Reset status to 'pending'
        tourRequest.setUpdateAt(LocalDateTime.now());

        TourRequest updatedTourRequest = tourRequestsRepository.save(tourRequest);

        return tourRequestMapper.mapTourRequestToResponse(updatedTourRequest);
    }

    @Transactional
    public TourRequestCancelResponse cancelTourRequest(Long id) {
        TourRequest tourRequest = tourRequestsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tour request not found"));


        //  tourRequest.setStatus(CANCELED_STATUS);
        tourRequest = tourRequestsRepository.save(tourRequest);

        return mapToResponse(tourRequest);
    }

    private TourRequestCancelResponse mapToResponse(TourRequest tourRequest) {
        TourRequestCancelResponse response = new TourRequestCancelResponse();
        response.setId(tourRequest.getId());
        response.setTourDate(tourRequest.getTourDate());
        response.setTourTime(tourRequest.getTourTime());
        response.setStatus(tourRequest.getStatus());
        response.setAdvert(tourRequest.getAdvert());
        response.setOwnerUser(tourRequest.getOwnerUser());
        response.setGuestUser(tourRequest.getGuestUser());
        response.setCreateAt(tourRequest.getCreateAt());
        response.setUpdateAt(tourRequest.getUpdateAt());
        return response;
    }

    @Transactional
    public TourRequestCancelResponse approveTourRequest(Long id) {

        TourRequest tourRequest = tourRequestsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tour request not found"));


        // tourRequest.setStatus(APPROVED_STATUS);
        tourRequest = tourRequestsRepository.save(tourRequest);

        // Güncellenmiş tur talebini DTO'ya dönüştür
        return mapToResponse(tourRequest);
    }



    @Transactional
    public TourRequestCancelResponse declineTourRequest(Long id) {
        // Tur talebini veritabanından al
        TourRequest tourRequest = tourRequestsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tour request not found"));


        // tourRequest.setStatus(DECLINED_STATUS);
        tourRequest = tourRequestsRepository.save(tourRequest);


        return mapToResponse(tourRequest);
    }*/
//u.h***************************************************


    private final TourRequestsRepository tourRequestRepository;
    public int countTourRequestsByAdvertId(Long id) {
        return tourRequestRepository.countTourRequestsByAdvertId(id);
    }
    public List<TourRequest> getTourRequestsByAdvertId(Long advertId) {
        return tourRequestRepository.findByAdvertId(advertId);
    }
    public void deleteByAdvertId(Long advertId) {
        tourRequestRepository.deleteByAdvertId(advertId);
    }

}
