package com.broker.broker.repository;

import com.broker.broker.domain.Provajder;
import com.broker.broker.domain.Servis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServisRepository extends JpaRepository<Servis,Long> {
    Servis findByNameAndProvajder(String name, Provajder provajder);
}
