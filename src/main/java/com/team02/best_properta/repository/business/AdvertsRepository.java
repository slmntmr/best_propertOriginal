package com.team02.best_properta.repository.business;


import com.team02.best_properta.entity.concretes.business.Advert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertsRepository extends JpaRepository<Advert, Long> {


    List<Advert> findByUserId(Long userId);


    boolean existsByUserId(Long userId);
}
