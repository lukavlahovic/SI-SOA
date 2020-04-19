package com.broker.broker.repository;

import com.broker.broker.domain.Endpoint;
import com.broker.broker.domain.Provajder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EndpointRepository extends JpaRepository<Endpoint,Long> {
    Endpoint findByRuta(String ruta);
}
