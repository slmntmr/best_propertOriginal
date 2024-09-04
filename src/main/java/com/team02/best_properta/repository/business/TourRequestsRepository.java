package com.team02.best_properta.repository.business;


import com.team02.best_properta.entity.concretes.business.TourRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRequestsRepository extends JpaRepository<TourRequest, Long> {

    /*List<TourRequest> findByUserId(Long userId);*/

    boolean existsByOwnerUserIdOrGuestUserId(Long ownerUserId, Long guestUserId);
}
