package com.team02.best_properta.repository.business;


import com.team02.best_properta.entity.concretes.business.Advert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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


    @Query("SELECT a FROM Advert a " +
            "WHERE (:q IS NULL OR (LOWER(a.title) LIKE LOWER(CONCAT('%', :q, '%')) " +
            "OR LOWER(a.description) LIKE LOWER(CONCAT('%', :q, '%')))) " +
            "AND (:category_id IS NULL OR a.category.id = :category_id) " +
            "AND (:advert_type_id IS NULL OR a.advertType.id = :advert_type_id) " +
            "AND (:price_start IS NULL OR a.price >= :price_start) " +
            "AND (:price_end IS NULL OR a.price <= :price_end) " +
            "AND (:status IS NULL OR a.status = :status)")
    Page<Advert> findAdverts(@Param("q") String q,
                             @Param("category_id") Long category_id,
                             @Param("advert_type_id") Long advert_type_id,
                             @Param("price_start") BigDecimal price_start,
                             @Param("price_end") BigDecimal price_end,
                             @Param("status") Integer status,
                             Pageable pageable);
    /******************************************************************************************/
    @Query("SELECT a.city, COUNT(a) " +
            "FROM Advert a " +
            "GROUP BY a.city")
    List<Object[]> countAdvertsByCity();
    /******************************************************************************************/
    @Query("SELECT a.category.title, COUNT(a) " +
            "FROM Advert a " +
            "GROUP BY a.category.title")
    List<Object[]> findAdvertsGroupedByCategory();
    /******************************************************************************************/
    @Query("SELECT a FROM Advert a")
    List<Advert> findAllAdverts();
    /******************************************************************************************/
    @Query("SELECT a FROM Advert a WHERE " +
            "(:q IS NULL OR (LOWER(a.title) LIKE LOWER(CONCAT('%', :q, '%')) " +
            "OR LOWER(a.description) LIKE LOWER(CONCAT('%', :q, '%')))) " +
            "AND (:categoryId IS NULL OR a.category.id = :categoryId) " +
            "AND (:advertTypeId IS NULL OR a.advertType.id = :advertTypeId) " +
            "AND (:priceStart IS NULL OR a.price >= :priceStart) " +
            "AND (:priceEnd IS NULL OR a.price <= :priceEnd) " +
            "AND (:status IS NULL OR a.status = :status)")
    Page<Advert> findAdvertsAdmin(@Param("q") String q,
                                  @Param("categoryId") Long categoryId,
                                  @Param("advertTypeId") Long advertTypeId,
                                  @Param("priceStart") Double priceStart,
                                  @Param("priceEnd") Double priceEnd,
                                  @Param("status") Integer status,
                                  Pageable pageable);
    /******************************************************************************************/
    Optional<Advert> findBySlug(String slug);
    /******************************************************************************************/
    Optional<Advert> findByIdAndUserIdIsNotNull(Long id);
    /******************************************************************************************/
    @Query("SELECT COUNT(a) > 0 FROM Advert a WHERE a.id = :id AND a.built_in = true")
    boolean existsByIdAndBuiltInTrue(@Param("id") Long id);



}
