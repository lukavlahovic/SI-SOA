package com.broker.broker.repository;

import com.broker.broker.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {


}
