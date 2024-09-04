package com.team02.best_properta.repository.business;

import com.team02.best_properta.entity.concretes.business.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogsRepository extends JpaRepository<Log, Long> {
    void deleteByUserId(Long userId);
}
