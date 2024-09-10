package com.team02.best_properta.repository.business;

import com.team02.best_properta.entity.concretes.business.AdvertType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertTypeRepository extends JpaRepository<AdvertType,Long> {

    /*-----Mx Mustafa------Report katmanı için AdvertType Counter-----START*/
    @Query("SELECT COUNT(at) FROM AdvertType at")
    int countAdvertTypes();
    /*-----Mx Mustafa------Report katmanı için AdvertType Counter-----END*/

    @Query("SELECT a FROM AdvertType a WHERE a.title = :title")
    AdvertType findByTitle(@Param("title") String title);



}