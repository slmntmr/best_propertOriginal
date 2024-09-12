package com.team02.best_properta.repository.business;



import com.team02.best_properta.entity.concretes.business.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    // Optional<Image> findByAdvertIdAndFeaturedTrue(Long advertId);

    // Belirli bir advert_id'ye sahip tüm resimlerin featured alanını false olarak günceller
    @Modifying
    @Query("UPDATE Image i SET i.featured = false WHERE i.advert.id = :advertId")
    void updateAllFeaturedByAdvertId(Long advertId);



    List<Image> findByAdvertId(Long advertId);  // Advert ID'ye göre image'leri getiren method
    void deleteByAdvertId(Long advertId);
}