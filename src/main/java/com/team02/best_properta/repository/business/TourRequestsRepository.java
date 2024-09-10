package com.team02.best_properta.repository.business;


import com.team02.best_properta.entity.concretes.business.TourRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TourRequestsRepository extends JpaRepository<TourRequest, Long> {

    /*List<TourRequest> findByUserId(Long userId);*/

    boolean existsByOwnerUserIdOrGuestUserId(Long ownerUserId, Long guestUserId);



    @Query("SELECT COUNT(tr) FROM TourRequest tr")
    long countTourRequests();

    @Query("SELECT tr FROM TourRequest tr WHERE " +
            "(:date1 IS NULL OR tr.tourDate >= :date1) AND " +
            "(:date2 IS NULL OR tr.tourDate <= :date2) AND " +
            "(:status IS NULL OR tr.status = :status)")
    List<TourRequest> findTourRequests(
            @Param("date1") LocalDate date1,
            @Param("date2") LocalDate date2,
            @Param("status") String status);




    List<TourRequest> findByOwnerUser_Id(Long ownerId);

    TourRequest findByIdAndOwnerUserId(Long id, Long ownerId);

  //  boolean existsByOwnerUserIdOrGuestUserId(Long ownerUserId, Long guestUserId);

    Optional<TourRequest> findById(Long id);

}
