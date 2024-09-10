package com.team02.best_properta.repository.business;


import com.team02.best_properta.entity.concretes.business.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AdvertsRepository extends JpaRepository<Advert, Long> {


    List<Advert> findByUserId(Long userId);


    boolean existsByUserId(Long userId);

    /*-----Mx Mustafa------Report katmanı için Advert Finder-----START*/
    @Query("SELECT a FROM Advert a WHERE a.createAt BETWEEN :startDate AND :endDate AND a.location = :location AND a.advertType = :type AND a.status = :status")
    List<Advert> findAdvertsByCriteria(LocalDateTime startDate, LocalDateTime endDate, String location, String type, String status);
    /*-----Mx Mustafa------Report katmanı için Advert Finder-----END*/

    /*-----Mx Mustafa------Report katmanı için Active Advert Finder-----START*/
    @Query("SELECT COUNT(a) FROM Advert a WHERE a.status = 1 ")
    int countActivatedAdverts();
    /*-----Mx Mustafa------Report katmanı için Active Advert Finder-----END*/

    /*-----Mx Mustafa------Report katmanı için Popular Advert Finder-----START*/
    @Query("SELECT a FROM Advert a ORDER BY a.viewCount DESC")
    List<Advert> findMostPopularProperties(@Param("amount") int amount);

    boolean existsByAdvertTypeId(Long id);


    /*boolean existsByAdvertTypeId(Long id);
    *//*-----Mx Mustafa------Report katmanı için Popular Advert Finder-----END*//*
    */
}
