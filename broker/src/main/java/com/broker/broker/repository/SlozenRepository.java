package com.broker.broker.repository;

import com.broker.broker.domain.Provajder;
import com.broker.broker.domain.Servis;
import com.broker.broker.domain.SlozenServis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlozenRepository extends JpaRepository<SlozenServis,Long> {
    SlozenServis findByNameAndProvajderS(String name, Provajder provajderS);
}
